package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.PagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pago")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/registrar")
    public Pago registrar(@RequestBody Pago pago) {
        return pagoService.registrarPago(pago);
    }
}