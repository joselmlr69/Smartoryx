package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuService;

	@GetMapping("/listar")
	public List<Usuario> listarUsuarios() {
		return usuService.listarUsuarios();
	}

	@GetMapping("/{id}")
	public Usuario obtenerUsuario(@PathVariable("id") int id_usuario) {
		return usuService.obtenerUsuario(id_usuario);
	}

	@PostMapping("/agregar")
	public Usuario crearUsuario(@RequestBody Usuario usuario) {
		return usuService.agregarUsuario(usuario);
	}

	@PutMapping("/{id}")
	public Usuario actualizarUsuario(@PathVariable("id") int id_usuario, @RequestBody Usuario usuario,
			HttpSession session) {

		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

		// 🔒 Validación de sesión
		if (usuarioLogueado == null) {
			throw new RuntimeException("Sesión expirada o no autenticado");
		}

		System.out.println("Usuario en sesión: " + usuarioLogueado);

		return usuService.actualizarUsuario(id_usuario, usuario, usuarioLogueado);
	}

	@DeleteMapping("/{id}")
	public void eliminarUsuario(@PathVariable("id") int id_usuario, HttpSession session) {

		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

		if (usuarioLogueado == null) {
			throw new RuntimeException("Sesión expirada o no autenticado");
		}

		usuService.eliminarUsuario(id_usuario, usuarioLogueado);
	}

	@PostMapping("/login")
	public Usuario login(@RequestBody Usuario usuario, HttpSession session) {

		Usuario u = usuService.login(usuario.getCorreo(), usuario.getPassword());

		session.setAttribute("usuario", u); // guardar sesión

		System.out.println("Usuario en sesión (login): " + u);

		return u;
	}
}
