package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Envio;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.EnvioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/envio")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping("/listar")
    public List<Envio> listar() {
        return envioService.listar();
    }

    @GetMapping("/{id}")
    public Envio obtener(@PathVariable Integer id) {
        return envioService.obtenerPorId(id);
    }

    @PostMapping("/agregar")
    public Envio guardar(@RequestBody Envio envio) {
        return envioService.guardar(envio.getVenta().getId_venta(), envio.getDireccion().getId_direccion(), envio);
    }

    @PutMapping("/{id}")
    public Envio actualizar(@PathVariable Integer id, @RequestBody Envio envio) {
        return envioService.actualizar(id, envio);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        envioService.eliminar(id);
    }
}