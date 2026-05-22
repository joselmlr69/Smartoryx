package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.CarritoAgregarRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.CarritoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.CarritoMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.CarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

	private final CarritoService carritoService;
	private final CarritoMapper carritoMapper;

	@GetMapping("/{idUsuario}")
	public CarritoResponse obtenerOCrear(@PathVariable Integer idUsuario) {
		Carrito carrito = carritoService.obtenerOCrearCarrito(idUsuario);
		CarritoResponse response = carritoMapper.toResponse(carrito);
		response.setTotal(carritoService.calcularTotal(idUsuario));
		return response;
	}

	@PostMapping("/agregar/{idUsuario}")
	public CarritoResponse agregarProducto(@PathVariable Integer idUsuario,
			@Valid @RequestBody CarritoAgregarRequest request) {
		Carrito carrito = carritoService.agregarProducto(idUsuario, request.getIdProducto(), request.getCantidad());
		CarritoResponse response = carritoMapper.toResponse(carrito);
		response.setTotal(carritoService.calcularTotal(idUsuario));
		return response;
	}

	@DeleteMapping("/eliminar/{idUsuario}/{idProducto}")
	public ResponseEntity<Void> eliminarProducto(@PathVariable Integer idUsuario, @PathVariable Integer idProducto) {
		carritoService.eliminarProducto(idUsuario, idProducto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/vaciar/{idUsuario}")
	public ResponseEntity<Void> vaciarCarrito(@PathVariable Integer idUsuario) {
		carritoService.vaciarCarrito(idUsuario);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/total/{idUsuario}")
	public Double calcularTotal(@PathVariable Integer idUsuario) {
		return carritoService.calcularTotal(idUsuario);
	}
}
