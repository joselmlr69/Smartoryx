package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.LoginRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.UsuarioRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.UsuarioResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.UnauthorizedException;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.UsuarioMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuService;
	private final UsuarioMapper usuarioMapper;

	@GetMapping("/listar")
	public List<UsuarioResponse> listarUsuarios() {
		return usuService.listarUsuarios().stream()
				.map(usuarioMapper::toResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public UsuarioResponse obtenerUsuario(@PathVariable("id") int id_usuario) {
		return usuarioMapper.toResponse(usuService.obtenerUsuario(id_usuario));
	}

	@PostMapping("/agregar")
	public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest request) {
		Usuario usuario = usuarioMapper.toEntity(request);
		return usuarioMapper.toResponse(usuService.agregarUsuario(usuario));
	}

	@PutMapping("/{id}")
	public UsuarioResponse actualizarUsuario(@PathVariable("id") int id_usuario,
			@Valid @RequestBody UsuarioRequest request, HttpSession session) {
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
		if (usuarioLogueado == null) {
			throw new UnauthorizedException("Sesión expirada o no autenticado");
		}
		Usuario usuario = usuarioMapper.toEntity(request);
		usuario.setPassword(request.getPassword());
		return usuarioMapper.toResponse(usuService.actualizarUsuario(id_usuario, usuario, usuarioLogueado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") int id_usuario, HttpSession session) {
		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");
		if (usuarioLogueado == null) {
			throw new UnauthorizedException("Sesión expirada o no autenticado");
		}
		usuService.eliminarUsuario(id_usuario, usuarioLogueado);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public UsuarioResponse login(@Valid @RequestBody LoginRequest request, HttpSession session) {
		Usuario u = usuService.login(request.getCorreo(), request.getPassword());
		session.setAttribute("usuario", u);
		return usuarioMapper.toResponse(u);
	}

	@GetMapping("/sesion")
	public ResponseEntity<UsuarioResponse> obtenerSesion(HttpSession session) {
		Usuario u = (Usuario) session.getAttribute("usuario");
		if (u == null) {
			return ResponseEntity.status(401).build();
		}
		return ResponseEntity.ok(usuarioMapper.toResponse(u));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		return ResponseEntity.noContent().build();
	}
}
