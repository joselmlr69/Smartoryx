package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ResourceNotFoundException;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Direccion;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.DireccionRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.UsuarioRepository;

@Service
public class DireccionService {

	@Autowired
	private DireccionRepository direccionRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;

	public List<Direccion> listar() {
		return direccionRepo.findAll();
	}

	public Direccion obtenerPorId(Integer id) {
		return direccionRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dirección no existe"));
	}

	public Direccion guardar(Integer id_usuario, Direccion direccion) {
		Usuario usuario = usuarioRepo.findById(id_usuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no existe"));
		direccion.setUsuario(usuario);
		return direccionRepo.save(direccion);
	}

	public Direccion actualizar(Integer id_direccion, Direccion nuevaDireccion) {
		Direccion direccion = direccionRepo.findById(id_direccion)
				.orElseThrow(() -> new ResourceNotFoundException("Dirección no existe"));
		direccion.setDireccion(nuevaDireccion.getDireccion());
		direccion.setCiudad(nuevaDireccion.getCiudad());
		direccion.setReferencia(nuevaDireccion.getReferencia());
		return direccionRepo.save(direccion);
	}

	public void eliminar(Integer id) {
		Direccion direccion = direccionRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dirección no existe"));
		direccionRepo.delete(direccion);
	}

	public List<Direccion> listarPorUsuario(Integer id_usuario) {
		return direccionRepo.listarPorUsuario(id_usuario);
	}
}
