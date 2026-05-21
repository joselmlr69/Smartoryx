package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping("/{idUsuario}")
    public Carrito obtenerOCrear(@PathVariable Integer idUsuario) {
        return carritoService.obtenerOCrearCarrito(idUsuario);
    }

    @PostMapping("/agregar/{idUsuario}/{idProducto}/{cantidad}")
    public Carrito agregarProducto(@PathVariable Integer idUsuario,
                                   @PathVariable Integer idProducto,
                                   @PathVariable Integer cantidad) {
        return carritoService.agregarProducto(idUsuario, idProducto, cantidad);
    }

    @DeleteMapping("/eliminar/{idUsuario}/{idProducto}")
    public void eliminarProducto(@PathVariable Integer idUsuario,
                                 @PathVariable Integer idProducto) {
        carritoService.eliminarProducto(idUsuario, idProducto);
    }

    @DeleteMapping("/vaciar/{idUsuario}")
    public void vaciarCarrito(@PathVariable Integer idUsuario) {
        carritoService.vaciarCarrito(idUsuario);
    }

    @GetMapping("/total/{idUsuario}")
    public Double calcularTotal(@PathVariable Integer idUsuario) {
        return carritoService.calcularTotal(idUsuario);
    }
}