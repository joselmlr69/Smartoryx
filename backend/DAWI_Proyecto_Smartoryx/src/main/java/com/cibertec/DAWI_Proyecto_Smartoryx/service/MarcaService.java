package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Marca;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository repo;
	
	public List<Marca> listarMarcas(){
		return repo.findAll();
	}
	
	public Marca obtenerMarca(int id_marca) {
		return repo.findById(id_marca).orElse(null);
	}
	
	public Marca agregarMarca(Marca marca) {
		return repo.save(marca);
	}
	public Marca actualizarMarca(Integer id_marca,Marca marca){
		 Marca m = repo.findById(id_marca
				 )
	                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

	        m.setNombre(marca.getNombre());
	        return repo.save(m);
	}
}
