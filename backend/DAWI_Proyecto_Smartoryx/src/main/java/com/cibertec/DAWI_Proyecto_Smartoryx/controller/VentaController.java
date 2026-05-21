package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.VentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping("/registrar")
    public Venta registrarVenta(@RequestBody Venta venta) {
        return ventaService.registrarVenta(venta);
    }

    @PostMapping("/generar-desde-carrito/{idUsuario}")
    public Venta generarVentaDesdeCarrito(@PathVariable Integer idUsuario) {
        return ventaService.generarVentaDesdeCarrito(idUsuario);
    }

    @PostMapping("/pagar/{idVenta}/{idMetodo}")
    public Pago pagarVenta(@PathVariable Integer idVenta, @PathVariable Integer idMetodo) {
        return ventaService.pagarVenta(idVenta, idMetodo);
    }
}