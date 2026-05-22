package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.PagoRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.VentaRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.PagoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.VentaResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.PagoMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.VentaMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.DetalleVenta;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.VentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {

	private final VentaService ventaService;
	private final VentaMapper ventaMapper;
	private final PagoMapper pagoMapper;

	@PostMapping("/registrar")
	public VentaResponse registrarVenta(@Valid @RequestBody VentaRequest request) {
		Venta venta = new Venta();
		venta.setUsuario(new com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario());
		venta.getUsuario().setId_usuario(request.getIdUsuario());

		List<DetalleVenta> detalles = request.getDetalles().stream()
				.map(d -> {
					DetalleVenta dv = new DetalleVenta();
					Producto prod = new Producto();
					prod.setId_producto(d.getIdProducto());
					dv.setProducto(prod);
					dv.setCantidad(d.getCantidad());
					return dv;
				})
				.toList();
		venta.setDetalles(detalles);

		return ventaMapper.toResponse(ventaService.registrarVenta(venta));
	}

	@PostMapping("/generar-desde-carrito/{idUsuario}")
	public VentaResponse generarVentaDesdeCarrito(@PathVariable Integer idUsuario) {
		return ventaMapper.toResponse(ventaService.generarVentaDesdeCarrito(idUsuario));
	}

	@PostMapping("/pagar/{idVenta}/{idMetodo}")
	public PagoResponse pagarVenta(@PathVariable Integer idVenta, @PathVariable Integer idMetodo) {
		return pagoMapper.toResponse(ventaService.pagarVenta(idVenta, idMetodo));
	}

	@PostMapping("/pagar")
	public PagoResponse pagarVentaConBody(@Valid @RequestBody PagoRequest request) {
		return pagoMapper.toResponse(ventaService.pagarVenta(request.getIdVenta(), request.getIdMetodo()));
	}

	@GetMapping("/usuario/{idUsuario}")
	public List<VentaResponse> listarPorUsuario(@PathVariable Integer idUsuario) {
		return ventaService.listarPorUsuario(idUsuario).stream()
				.map(ventaMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public VentaResponse obtenerVenta(@PathVariable Integer id) {
		return ventaMapper.toResponse(ventaService.obtenerVentaConDetalles(id));
	}
}
