package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.CarritoDetalle;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.CarritoDetalleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carrito-detalle")
@RequiredArgsConstructor
public class CarritoDetalleController {

    private final CarritoDetalleService detalleService;

    @GetMapping("/listar")
    public List<CarritoDetalle> listar() {
        return detalleService.listar();
    }

    @GetMapping("/{id}")
    public CarritoDetalle obtener(@PathVariable Integer id) {
        return detalleService.obtenerPorId(id);
    }

    @PostMapping("/agregar/{idCarrito}/{idProducto}/{cantidad}")
    public CarritoDetalle guardar(@PathVariable Integer idCarrito,
                                  @PathVariable Integer idProducto,
                                  @PathVariable Integer cantidad) {
        return detalleService.guardar(idCarrito, idProducto, cantidad);
    }

    @PutMapping("/{id}/{cantidad}")
    public CarritoDetalle actualizar(@PathVariable Integer id,
                                     @PathVariable Integer cantidad) {
        return detalleService.actualizar(id, cantidad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        detalleService.eliminar(id);
    }

    @GetMapping("/carrito/{idCarrito}")
    public List<CarritoDetalle> listarPorCarrito(@PathVariable Integer idCarrito) {
        return detalleService.listarPorCarrito(idCarrito);
    }
}