package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.MarcaResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.MarcaMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.MarcaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaController {

	private final MarcaService marcaService;
	private final MarcaMapper marcaMapper;

	@GetMapping("/listar")
	public List<MarcaResponse> listar() {
		return marcaService.listarMarcas().stream()
				.map(marcaMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public MarcaResponse obtener(@PathVariable Integer id) {
		return marcaMapper.toResponse(marcaService.obtenerMarca(id));
	}

	@PostMapping("/agregar")
	public MarcaResponse guardar(@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Marca marca) {
		return marcaMapper.toResponse(marcaService.agregarMarca(marca));
	}

	@PutMapping("/{id}")
	public MarcaResponse actualizar(@PathVariable Integer id,
			@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Marca marca) {
		return marcaMapper.toResponse(marcaService.actualizarMarca(id, marca));
	}
}
