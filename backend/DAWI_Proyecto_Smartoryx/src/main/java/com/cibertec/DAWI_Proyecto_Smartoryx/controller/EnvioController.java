package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.EnvioResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.EnvioMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.EnvioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/envio")
@RequiredArgsConstructor
public class EnvioController {

	private final EnvioService envioService;
	private final EnvioMapper envioMapper;

	@GetMapping("/listar")
	public List<EnvioResponse> listar() {
		return envioService.listar().stream()
				.map(envioMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public EnvioResponse obtener(@PathVariable Integer id) {
		return envioMapper.toResponse(envioService.obtenerPorId(id));
	}

	@PostMapping("/agregar")
	public EnvioResponse guardar(@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Envio envio) {
		return envioMapper.toResponse(envioService.guardar(envio.getVenta().getId_venta(), envio.getDireccion().getId_direccion(), envio));
	}

	@PutMapping("/{id}")
	public EnvioResponse actualizar(@PathVariable Integer id,
			@RequestBody com.cibertec.DAWI_Proyecto_Smartoryx.model.Envio envio) {
		return envioMapper.toResponse(envioService.actualizar(id, envio));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
		envioService.eliminar(id);
		return ResponseEntity.noContent().build();
	}
}
