package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.PagoRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.PagoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.PagoMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.PagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pago")
@RequiredArgsConstructor
public class PagoController {

	private final PagoService pagoService;
	private final PagoMapper pagoMapper;

	@PostMapping("/registrar")
	public PagoResponse registrar(@Valid @RequestBody PagoRequest request) {
		com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago pago = new com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago();
		com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta venta = new com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta();
		venta.setId_venta(request.getIdVenta());
		com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago metodo = new com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago();
		metodo.setId_metodo(request.getIdMetodo());
		pago.setVenta(venta);
		pago.setMetodoPago(metodo);
		return pagoMapper.toResponse(pagoService.registrarPago(pago));
	}

	@GetMapping("/venta/{idVenta}")
	public List<PagoResponse> listarPorVenta(@PathVariable Integer idVenta) {
		return pagoService.listarPorVenta(idVenta).stream()
				.map(pagoMapper::toResponse)
				.toList();
	}
}
