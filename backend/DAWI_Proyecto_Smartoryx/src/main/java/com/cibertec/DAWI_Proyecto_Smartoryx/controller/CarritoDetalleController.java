package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.CarritoDetalleResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.CarritoDetalleMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.CarritoDetalleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carrito-detalle")
@RequiredArgsConstructor
public class CarritoDetalleController {

	private final CarritoDetalleService detalleService;
	private final CarritoDetalleMapper carritoDetalleMapper;

	@GetMapping("/listar")
	public List<CarritoDetalleResponse> listar() {
		return detalleService.listar().stream()
				.map(carritoDetalleMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public CarritoDetalleResponse obtener(@PathVariable Integer id) {
		return carritoDetalleMapper.toResponse(detalleService.obtenerPorId(id));
	}

	@PostMapping("/agregar/{idCarrito}/{idProducto}/{cantidad}")
	public CarritoDetalleResponse guardar(@PathVariable Integer idCarrito,
			@PathVariable Integer idProducto, @PathVariable Integer cantidad) {
		return carritoDetalleMapper.toResponse(detalleService.guardar(idCarrito, idProducto, cantidad));
	}

	@PutMapping("/{id}/{cantidad}")
	public CarritoDetalleResponse actualizar(@PathVariable Integer id, @PathVariable Integer cantidad) {
		return carritoDetalleMapper.toResponse(detalleService.actualizar(id, cantidad));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		detalleService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/carrito/{idCarrito}")
	public List<CarritoDetalleResponse> listarPorCarrito(@PathVariable Integer idCarrito) {
		return detalleService.listarPorCarrito(idCarrito).stream()
				.map(carritoDetalleMapper::toResponse)
				.toList();
	}
}
