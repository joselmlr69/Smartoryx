package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.VentaDetalleResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.VentaDetalleMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.DetalleVentaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/detalle-venta")
@RequiredArgsConstructor
public class DetalleVentaController {

	private final DetalleVentaService detalleVentaService;
	private final VentaDetalleMapper ventaDetalleMapper;

	@GetMapping("/listar")
	public List<VentaDetalleResponse> listar() {
		return detalleVentaService.listar().stream()
				.map(ventaDetalleMapper::toResponse)
				.toList();
	}
}
