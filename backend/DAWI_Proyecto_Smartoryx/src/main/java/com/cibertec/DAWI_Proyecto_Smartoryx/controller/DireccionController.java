package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Direccion;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.DireccionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/direccion")
@RequiredArgsConstructor
public class DireccionController {

    private final DireccionService direccionService;

    @GetMapping("/listar")
    public List<Direccion> listar() {
        return direccionService.listar();
    }

    @GetMapping("/{id}")
    public Direccion obtener(@PathVariable Integer id) {
        return direccionService.obtenerPorId(id);
    }

    @PostMapping("/agregar/{idUsuario}")
    public Direccion guardar(@PathVariable Integer idUsuario, @RequestBody Direccion direccion) {
        return direccionService.guardar(idUsuario, direccion);
    }

    @PutMapping("/{id}")
    public Direccion actualizar(@PathVariable Integer id, @RequestBody Direccion direccion) {
        return direccionService.actualizar(id, direccion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        direccionService.eliminar(id);
    }
}