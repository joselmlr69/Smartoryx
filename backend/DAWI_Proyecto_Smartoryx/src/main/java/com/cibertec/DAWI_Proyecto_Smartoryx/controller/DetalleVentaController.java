package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.DetalleVenta;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.DetalleVentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detalle-venta")
@RequiredArgsConstructor
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    @GetMapping("/listar")
    public List<DetalleVenta> listar() {
        return detalleVentaService.listar();
    }
}