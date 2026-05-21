package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.MetodoPagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/metodo-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    @GetMapping("/listar")
    public List<MetodoPago> listar() {
        return metodoPagoService.listar();
    }

    @GetMapping("/{id}")
    public MetodoPago obtener(@PathVariable Integer id) {
        return metodoPagoService.obtenerPorId(id);
    }
}