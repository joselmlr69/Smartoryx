package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.exception.BadRequestException;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ResourceNotFoundException;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.CarritoDetalle;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.DetalleVenta;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.MovimientoStock;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CarritoDetalleRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CarritoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.DetalleVentaRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MetodoPagoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MovimientoStockRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.PagoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProductoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

	@Autowired
	private VentaRepository ventaRepo;
	@Autowired
	private ProductoRepository productoRepo;
	@Autowired
	private CarritoRepository carritoRepo;
	@Autowired
	private DetalleVentaRepository detalleRepo;
	@Autowired
	private MovimientoStockRepository movRepo;
	@Autowired
	private MetodoPagoRepository metodoRepo;
	@Autowired
	private PagoRepository pagoRepo;
	@Autowired
	private AuditoriaService auditoriaService;

	@Transactional
	public Venta registrarVenta(Venta venta) {
		double total = 0;

		for (DetalleVenta det : venta.getDetalles()) {
			Producto prod = productoRepo.findById(det.getProducto().getId_producto())
					.orElseThrow(() -> new ResourceNotFoundException("Producto no existe"));

			if (prod.getStock() < det.getCantidad()) {
				throw new BadRequestException("Stock insuficiente para: " + prod.getNombre());
			}

			det.setPrecio(prod.getPrecio());
			double subtotal = det.getCantidad() * prod.getPrecio();
			det.setSubtotal(subtotal);

			prod.setStock(prod.getStock() - det.getCantidad());
			productoRepo.save(prod);

			MovimientoStock mov = new MovimientoStock();
			mov.setProducto(prod);
			mov.setTipo("SALIDA");
			mov.setCantidad(det.getCantidad());
			mov.setFecha(LocalDateTime.now());
			movRepo.save(mov);

			det.setVenta(venta);
			total += subtotal;
		}

		venta.setTotal(total);
		venta.setFecha(new Date());
		venta.setEstado("PENDIENTE");

		Venta ventaGuardada = ventaRepo.save(venta);
		auditoriaService.registrar("tb_ventas", "INSERT",
				"Venta creada ID: " + ventaGuardada.getId_venta() + " | Total: " + ventaGuardada.getTotal(),
				ventaGuardada.getUsuario().getId_usuario());

		return ventaGuardada;
	}

	@Transactional
	public Venta generarVentaDesdeCarrito(Integer idUsuario) {
		Carrito carrito = carritoRepo.findCarritoByUsuario(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Carrito no existe"));

		if (carrito.getDetalles() == null || carrito.getDetalles().isEmpty()) {
			throw new BadRequestException("El carrito está vacío");
		}

		Usuario usuario = carrito.getUsuario();

		Venta venta = new Venta();
		venta.setUsuario(usuario);
		venta.setEstado("PENDIENTE");
		venta.setFecha(new Date());

		List<DetalleVenta> detallesVenta = new ArrayList<>();
		double total = 0;

		for (CarritoDetalle d : carrito.getDetalles()) {
			Producto producto = productoRepo.findById(d.getProducto().getId_producto())
					.orElseThrow(() -> new ResourceNotFoundException("Producto no existe"));

			if (producto.getStock() < d.getCantidad()) {
				throw new BadRequestException("Stock insuficiente para: " + producto.getNombre());
			}

			DetalleVenta dv = new DetalleVenta();
			dv.setProducto(producto);
			dv.setCantidad(d.getCantidad());
			dv.setPrecio(producto.getPrecio());
			dv.setSubtotal(producto.getPrecio() * d.getCantidad());
			dv.setVenta(venta);

			total += dv.getSubtotal();
			detallesVenta.add(dv);

			producto.setStock(producto.getStock() - d.getCantidad());
			productoRepo.save(producto);

			MovimientoStock mov = new MovimientoStock();
			mov.setProducto(producto);
			mov.setTipo("SALIDA");
			mov.setCantidad(d.getCantidad());
			mov.setFecha(LocalDateTime.now());
			movRepo.save(mov);
		}

		venta.setTotal(total);
		Venta ventaGuardada = ventaRepo.save(venta);

		auditoriaService.registrar("tb_ventas", "INSERT",
				"Venta generada desde carrito ID: " + ventaGuardada.getId_venta() +
				" | Usuario: " + usuario.getId_usuario() +
				" | Total: " + ventaGuardada.getTotal(),
				usuario.getId_usuario());

		for (DetalleVenta dv : detallesVenta) {
			dv.setVenta(ventaGuardada);
			detalleRepo.save(dv);
		}

		carrito.getDetalles().clear();
		carritoRepo.save(carrito);

		return ventaGuardada;
	}

	@Transactional
	public Pago pagarVenta(Integer idVenta, Integer idMetodo) {
		Venta venta = ventaRepo.findById(idVenta)
				.orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

		if ("PAGADO".equals(venta.getEstado())) {
			throw new BadRequestException("La venta ya fue pagada");
		}

		MetodoPago metodo = metodoRepo.findById(idMetodo)
				.orElseThrow(() -> new ResourceNotFoundException("Método de pago no existe"));

		Pago pago = new Pago();
		pago.setVenta(venta);
		pago.setMetodoPago(metodo);
		pago.setMonto(venta.getTotal());

		pagoRepo.save(pago);
		venta.setEstado("PAGADO");
		ventaRepo.save(venta);

		auditoriaService.registrar("tb_pagos", "INSERT",
				"Pago realizado | Venta ID: " + venta.getId_venta() +
				" | Monto: " + venta.getTotal() +
				" | Método: " + metodo.getNombre(),
				venta.getUsuario().getId_usuario());

		return pago;
	}

	public List<Venta> listarPorUsuario(Integer idUsuario) {
		return ventaRepo.findByUsuarioId(idUsuario);
	}

	public Venta obtenerVentaConDetalles(Integer idVenta) {
		return ventaRepo.findByIdWithDetalles(idVenta)
				.orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));
	}
}
