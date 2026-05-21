package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.MovimientoStock;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MovimientoStockRepository;

@Service
public class MovimientoStockService {

	@Autowired
	 private MovimientoStockRepository repo;

	    public void registrarSalida(Producto producto, Integer cantidad) {
	        MovimientoStock mov = new MovimientoStock();
	        mov.setProducto(producto);
	        mov.setTipo("SALIDA");
	        mov.setCantidad(cantidad);
	        mov.setFecha(LocalDateTime.now());

	        repo.save(mov);
	    }
}
