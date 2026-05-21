package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.CarritoDetalle;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CarritoDetalleRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CarritoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProductoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.UsuarioRepository;

@Service
public class CarritoService {

	@Autowired
	private CarritoRepository carritoRepo;
    @Autowired
	private CarritoDetalleRepository detalleRepo;
    @Autowired
	private ProductoRepository productoRepo;
    @Autowired
	private UsuarioRepository usuarioRepo;

    // 🔥 1. Obtener o crear carrito
    public Carrito obtenerOCrearCarrito(Integer id_usuario) {

        return carritoRepo.findCarritoByUsuario(id_usuario)
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepo.findById(id_usuario)
                            .orElseThrow(() -> new RuntimeException("Usuario no existe"));

                    Carrito carrito = new Carrito();
                    carrito.setUsuario(usuario);

                    return carritoRepo.save(carrito);
                });
    }

    // 🔥 2. Agregar producto
    public Carrito agregarProducto(Integer id_usuario, Integer id_producto, Integer cantidad) {

        Carrito carrito = obtenerOCrearCarrito(id_usuario);

        Producto producto = productoRepo.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no existe"));

        CarritoDetalle detalle = detalleRepo
                .findDetalle(carrito.getId_carrito(), id_producto)
                .orElse(null);

        if (detalle != null) {
            // 🔁 ya existe → suma cantidad
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        } else {
            // ➕ nuevo producto
            detalle = new CarritoDetalle();
            detalle.setCarrito(carrito);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
        }

        detalleRepo.save(detalle);
        return carrito;
    }

    // 🔥 3. Quitar producto
    public void eliminarProducto(Integer idUsuario, Integer idProducto) {

        Carrito carrito = obtenerOCrearCarrito(idUsuario);

        CarritoDetalle detalle = detalleRepo
                .findDetalle(carrito.getId_carrito(), idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no está en carrito"));
        
        detalleRepo.delete(detalle);
    }

    // 🔥 4. Vaciar carrito
    public void vaciarCarrito(Integer idUsuario) {
        Carrito carrito = obtenerOCrearCarrito(idUsuario);
        detalleRepo.deleteAll(carrito.getDetalles());
    }

    // 🔥 5. Calcular total
    public Double calcularTotal(Integer idUsuario) {

        Carrito carrito = obtenerOCrearCarrito(idUsuario);

        return carrito.getDetalles().stream()
                .mapToDouble(d -> d.getProducto().getPrecio() * d.getCantidad())
                .sum();
    }
}
