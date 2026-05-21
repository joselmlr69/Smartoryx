package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

	@Autowired
    private MetodoPagoRepository repo;

    public List<MetodoPago> listar() {
        return repo.findAll();
    }

    public MetodoPago obtenerPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Método no encontrado"));
    }

}
