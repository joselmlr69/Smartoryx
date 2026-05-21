package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Rol;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.RolService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolController {

 private final RolService service;
 
 @GetMapping("/listar")
 public List<Rol>listarRol(){
	 return service.listar();
 }
}
