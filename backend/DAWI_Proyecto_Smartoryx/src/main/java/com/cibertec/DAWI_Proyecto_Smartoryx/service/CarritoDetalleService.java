package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.CarritoDetalle;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CarritoDetalleRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CarritoRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProductoRepository;

@Service
public class CarritoDetalleService {
	
	@Autowired
    private CarritoDetalleRepository detalleRepo;
    @Autowired
	private CarritoRepository carritoRepo;
    @Autowired
    private ProductoRepository productoRepo;

    public List<CarritoDetalle> listar() {
        return detalleRepo.findAll();
    }

    public CarritoDetalle obtenerPorId(Integer id) {
        return detalleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de carrito no existe"));
    }

    public CarritoDetalle guardar(Integer id_carrito, Integer id_producto, Integer cantidad) {

        Carrito carrito = carritoRepo.findById(id_carrito)
                .orElseThrow(() -> new RuntimeException("Carrito no existe"));

        Producto producto = productoRepo.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no existe"));

        CarritoDetalle detalle = new CarritoDetalle();
        detalle.setCarrito(carrito);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        return detalleRepo.save(detalle);
    }

    public CarritoDetalle actualizar(Integer id_detalle, Integer cantidad) {

        CarritoDetalle detalle = detalleRepo.findById(id_detalle)
                .orElseThrow(() -> new RuntimeException("Detalle de carrito no existe"));

        detalle.setCantidad(cantidad);

        return detalleRepo.save(detalle);
    }

    public void eliminar(Integer id) {
        CarritoDetalle detalle = detalleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de carrito no existe"));

        detalleRepo.delete(detalle);
    }

    public List<CarritoDetalle> listarPorCarrito(Integer id_carrito) {
        return detalleRepo.listarPorCarrito(id_carrito);
    }
}