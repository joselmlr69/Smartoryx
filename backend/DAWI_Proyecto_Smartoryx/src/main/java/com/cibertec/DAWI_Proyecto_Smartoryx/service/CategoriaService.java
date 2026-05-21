package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Categoria;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository catRepo;

	public List<Categoria> listarCategoria() {
		return catRepo.findAll();
	}

	public Categoria obtenerCategoria(Integer id) {
		return catRepo.findById(id).orElse(null);
	}

	public Categoria guardar(Categoria categoria) {
		return catRepo.save(categoria);
	}

	public Categoria actualizar(Integer id_categoria, Categoria categoria) {
		Categoria cat = obtenerCategoria(id_categoria);
		cat.setNombre(categoria.getNombre());
		return catRepo.save(cat);
	}
	 public void eliminar(Integer id_categoria) {
	        Categoria cat = obtenerCategoria(id_categoria);
	        catRepo.delete(cat); 
	    }

}
