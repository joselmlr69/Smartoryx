package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.DetalleVenta;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.DetalleVentaRepository;

@Service
public class DetalleVentaService {

	@Autowired
	private DetalleVentaRepository repo;
	
	public List<DetalleVenta> listar() {
        return repo.findAll();
    }
}
