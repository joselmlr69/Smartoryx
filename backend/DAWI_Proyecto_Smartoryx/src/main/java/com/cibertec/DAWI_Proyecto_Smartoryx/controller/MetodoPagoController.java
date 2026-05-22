package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.MetodoPagoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.MetodoPagoMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.MetodoPagoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/metodo-pago")
@RequiredArgsConstructor
public class MetodoPagoController {

	private final MetodoPagoService metodoPagoService;
	private final MetodoPagoMapper metodoPagoMapper;

	@GetMapping("/listar")
	public List<MetodoPagoResponse> listar() {
		return metodoPagoService.listar().stream()
				.map(metodoPagoMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public MetodoPagoResponse obtener(@PathVariable Integer id) {
		return metodoPagoMapper.toResponse(metodoPagoService.obtenerPorId(id));
	}
}
