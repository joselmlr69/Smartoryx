package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.RolResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.RolMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.RolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {

	private final RolService service;
	private final RolMapper rolMapper;

	@GetMapping("/listar")
	public List<RolResponse> listarRol() {
		return service.listar().stream()
				.map(rolMapper::toResponse)
				.toList();
	}
}
