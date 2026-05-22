package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.ProductoRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.ProductoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.ProductoMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoService productoService;
	private final ProductoMapper productoMapper;

	@GetMapping("/listar")
	public List<ProductoResponse> listar() {
		return productoService.listar().stream()
				.map(productoMapper::toResponse)
				.toList();
	}

	@GetMapping("/completo")
	public List<ProductoResponse> listarCompleto() {
		return productoService.listarCompleto().stream()
				.map(productoMapper::toResponse)
				.toList();
	}

	@GetMapping("/por-marca/{idMarca}")
	public List<ProductoResponse> listarPorMarca(@PathVariable Integer idMarca) {
		return productoService.listarPorMarca(idMarca).stream()
				.map(productoMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public ProductoResponse obtener(@PathVariable Integer id) {
		return productoMapper.toResponse(productoService.obtenerProducto(id));
	}

	@PostMapping("/agregar")
	public ProductoResponse guardar(@Valid @RequestBody ProductoRequest request) {
		Producto producto = productoMapper.toEntity(request);
		return productoMapper.toResponse(productoService.guardar(producto));
	}

	@PutMapping("/{id}")
	public ProductoResponse actualizar(@PathVariable Integer id, @Valid @RequestBody ProductoRequest request) {
		Producto producto = productoMapper.toEntity(request);
		return productoMapper.toResponse(productoService.actualizar(id, producto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		productoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
