package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.MovimientoStockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/movimiento-stock")
@RequiredArgsConstructor
public class MovimientoStockController {

    private final MovimientoStockService movimientoStockService;

    @PostMapping("/salida/{cantidad}")
    public void registrarSalida(@RequestBody Producto producto, @PathVariable Integer cantidad) {
        movimientoStockService.registrarSalida(producto, cantidad);
    }
}