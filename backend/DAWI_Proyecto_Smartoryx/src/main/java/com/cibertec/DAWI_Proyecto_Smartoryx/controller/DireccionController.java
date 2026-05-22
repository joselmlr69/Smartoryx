package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.DireccionRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.DireccionResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.DireccionMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Direccion;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.DireccionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/direccion")
@RequiredArgsConstructor
public class DireccionController {

	private final DireccionService direccionService;
	private final DireccionMapper direccionMapper;

	@GetMapping("/listar")
	public List<DireccionResponse> listar() {
		return direccionService.listar().stream()
				.map(direccionMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public DireccionResponse obtener(@PathVariable Integer id) {
		return direccionMapper.toResponse(direccionService.obtenerPorId(id));
	}

	@PostMapping("/agregar/{idUsuario}")
	public DireccionResponse guardar(@PathVariable Integer idUsuario,
			@Valid @RequestBody DireccionRequest request) {
		Direccion direccion = new Direccion();
		direccion.setDireccion(request.getDireccion());
		direccion.setCiudad(request.getCiudad());
		direccion.setReferencia(request.getReferencia());
		return direccionMapper.toResponse(direccionService.guardar(idUsuario, direccion));
	}

	@PutMapping("/{id}")
	public DireccionResponse actualizar(@PathVariable Integer id,
			@Valid @RequestBody DireccionRequest request) {
		Direccion direccion = new Direccion();
		direccion.setDireccion(request.getDireccion());
		direccion.setCiudad(request.getCiudad());
		direccion.setReferencia(request.getReferencia());
		return direccionMapper.toResponse(direccionService.actualizar(id, direccion));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		direccionService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/usuario/{idUsuario}")
	public List<DireccionResponse> listarPorUsuario(@PathVariable Integer idUsuario) {
		return direccionService.listarPorUsuario(idUsuario).stream()
				.map(direccionMapper::toResponse)
				.toList();
	}
}
