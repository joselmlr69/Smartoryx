package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Rol;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.RolRepository;

@Service
public class RolService {
	  @Autowired
	    private RolRepository repo;

	    public List<Rol> listar() {
	        return repo.findAll();
	    }

	    public Rol buscarPorId(Integer id) {
	        return repo.findById(id).orElse(null);
	    }
}
