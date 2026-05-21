package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Marca;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.MarcaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping("/listar")
    public List<Marca> listar() {
        return marcaService.listarMarcas();
    }

    @GetMapping("/{id}")
    public Marca obtener(@PathVariable Integer id) {
        return marcaService.obtenerMarca(id);
    }

    @PostMapping("/agregar")
    public Marca guardar(@RequestBody Marca marca) {
        return marcaService.agregarMarca(marca);
    }

    @PutMapping("/{id}")
    public Marca actualizar(@PathVariable Integer id, @RequestBody Marca marca) {
        return marcaService.actualizarMarca(id, marca);
    }
}