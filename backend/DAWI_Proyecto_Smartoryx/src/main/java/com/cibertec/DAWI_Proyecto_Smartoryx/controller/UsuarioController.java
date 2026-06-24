package com.cibertec.DAWI_Proyecto_Smartoryx.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.LoginRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.UsuarioRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.UsuarioUpdateRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.UsuarioResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.UnauthorizedException;
import com.cibertec.DAWI_Proyecto_Smartoryx.mapper.UsuarioMapper;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Rol;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuService;
	private final UsuarioMapper usuarioMapper;

	private final SecurityContextRepository securityContextRepository =
			new HttpSessionSecurityContextRepository();

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
			@Valid @RequestBody UsuarioUpdateRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			throw new UnauthorizedException("Sesión expirada o no autenticado");
		}
		String correo = auth.getName();
		Usuario usuarioLogueado = usuService.obtenerPorCorreo(correo);
		if (usuarioLogueado == null) {
			throw new UnauthorizedException("Usuario no encontrado");
		}
		Usuario usuario = new Usuario();
		usuario.setNombre(request.getNombre());
		usuario.setApellido(request.getApellido());
		usuario.setCorreo(request.getCorreo());
		usuario.setPassword(request.getPassword());
		usuario.setEstado(request.getEstado());
		if (request.getIdRol() != null) {
			Rol rol = new Rol();
			rol.setId_rol(request.getIdRol());
			usuario.setRol(rol);
		}
		return usuarioMapper.toResponse(usuService.actualizarUsuario(id_usuario, usuario, usuarioLogueado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable("id") int id_usuario) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated()) {
			throw new UnauthorizedException("Sesión expirada o no autenticado");
		}
		String correo = auth.getName();
		Usuario usuarioLogueado = usuService.obtenerPorCorreo(correo);
		if (usuarioLogueado == null) {
			throw new UnauthorizedException("Usuario no encontrado");
		}
		usuService.eliminarUsuario(id_usuario, usuarioLogueado);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public UsuarioResponse login(@Valid @RequestBody LoginRequest request,
								 HttpServletRequest httpRequest,
								 HttpServletResponse httpResponse,
								 HttpSession session) {
		Usuario u = usuService.login(request.getCorreo(), request.getPassword());

		String rolNombre = u.getRol().getNombre();
		Authentication auth = new UsernamePasswordAuthenticationToken(
				u.getCorreo(),
				null,
				List.of(new SimpleGrantedAuthority("ROLE_" + rolNombre))
		);

		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(auth);
		SecurityContextHolder.setContext(context);
		securityContextRepository.saveContext(context, httpRequest, httpResponse);

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
