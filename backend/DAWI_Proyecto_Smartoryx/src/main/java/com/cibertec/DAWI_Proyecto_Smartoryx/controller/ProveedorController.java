package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.ProveedorResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.ProveedorMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.ProveedorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/proveedor")
@RequiredArgsConstructor
public class ProveedorController {

	private final ProveedorService proveedorService;
	private final ProveedorMapper proveedorMapper;

	@GetMapping("/listar")
	public List<ProveedorResponse> listar() {
		return proveedorService.listar().stream()
				.map(proveedorMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public ProveedorResponse obtener(@PathVariable Integer id) {
		return proveedorMapper.toResponse(proveedorService.obtenerProveedor(id));
	}

	@PostMapping("/agregar")
	public ProveedorResponse guardar(@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Proveedor proveedor) {
		return proveedorMapper.toResponse(proveedorService.guardar(proveedor));
	}

	@PutMapping("/{id}")
	public ProveedorResponse actualizar(@PathVariable Integer id,
			@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Proveedor proveedor) {
		return proveedorMapper.toResponse(proveedorService.actualizar(id, proveedor));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		proveedorService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
