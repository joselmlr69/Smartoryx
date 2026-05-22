package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.service.MovimientoStockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movimiento-stock")
@RequiredArgsConstructor
public class MovimientoStockController {

	private final MovimientoStockService movimientoStockService;

	@PostMapping("/salida/{idProducto}/{cantidad}")
	public ResponseEntity<Void> registrarSalida(@PathVariable Integer idProducto, @PathVariable Integer cantidad) {
		movimientoStockService.registrarSalida(idProducto, cantidad);
		return ResponseEntity.ok().build();
	}
}
