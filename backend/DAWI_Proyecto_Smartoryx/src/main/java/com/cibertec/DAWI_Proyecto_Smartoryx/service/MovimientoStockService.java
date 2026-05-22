package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ResourceNotFoundException;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.MovimientoStock;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MovimientoStockRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.ProductoRepository;

@Service
public class MovimientoStockService {

	@Autowired
	private MovimientoStockRepository repo;
	@Autowired
	private ProductoRepository productoRepo;

	public void registrarSalida(Integer idProducto, Integer cantidad) {
		Producto producto = productoRepo.findById(idProducto)
				.orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

		MovimientoStock mov = new MovimientoStock();
		mov.setProducto(producto);
		mov.setTipo("SALIDA");
		mov.setCantidad(cantidad);
		mov.setFecha(LocalDateTime.now());
		repo.save(mov);
	}
}
