package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Categoria;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.CategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping("/listar")
    public List<Categoria> listar() {
        return categoriaService.listarCategoria();
    }

    @GetMapping("/{id}")
    public Categoria obtener(@PathVariable Integer id) {
        return categoriaService.obtenerCategoria(id);
    }

    @PostMapping("/agregar")
    public Categoria guardar(@RequestBody Categoria categoria) {
        return categoriaService.guardar(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizar(@PathVariable Integer id, @RequestBody Categoria categoria) {
        return categoriaService.actualizar(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        categoriaService.eliminar(id);
    }
}