package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.CategoriaResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.CategoriaMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

	private final CategoriaService categoriaService;
	private final CategoriaMapper categoriaMapper;

	@GetMapping("/listar")
	public List<CategoriaResponse> listar() {
		return categoriaService.listarCategoria().stream()
				.map(categoriaMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public CategoriaResponse obtener(@PathVariable Integer id) {
		return categoriaMapper.toResponse(categoriaService.obtenerCategoria(id));
	}

	@PostMapping("/agregar")
	public CategoriaResponse guardar(@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Categoria categoria) {
		return categoriaMapper.toResponse(categoriaService.guardar(categoria));
	}

	@PutMapping("/{id}")
	public CategoriaResponse actualizar(@PathVariable Integer id,
			@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Categoria categoria) {
		return categoriaMapper.toResponse(categoriaService.actualizar(id, categoria));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		categoriaService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
