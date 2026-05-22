package com.cibertec.DAWI_Proyecto_Smartoryx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.DAWI_Proyecto_Smartoryx.exception.BadRequestException;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ForbiddenException;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.ResourceNotFoundException;
import com.cibertec.DAWI_Proyecto_Smartoryx.exception.UnauthorizedException;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Rol;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.RolRepository;
import com.cibertec.DAWI_Proyecto_Smartoryx.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private RolRepository repoRol;
	@Autowired
	private AuditoriaService auditoriaService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Usuario> listarUsuarios() {
		return repo.listarUsuarios();
	}

	public Usuario obtenerUsuario(Integer id_usuario) {
		return repo.findById(id_usuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
	}

	public Usuario agregarUsuario(Usuario usuario) {
		if (repo.existsByCorreo(usuario.getCorreo())) {
			throw new BadRequestException("El correo ya está registrado");
		}

		usuario.setEstado(1);
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

		Integer idRol = usuario.getRol().getId_rol();
		Rol rol = repoRol.findById(idRol)
				.orElseThrow(() -> new ResourceNotFoundException("Rol no válido"));
		usuario.setRol(rol);

		Usuario nuevo = repo.save(usuario);
		auditoriaService.registrar("tb_usuarios", "INSERT", "Se creó usuario ID: " + nuevo.getId_usuario(), 1);

		return nuevo;
	}

	public Usuario actualizarUsuario(Integer id_usuario, Usuario usuario, Usuario usuarioLogueado) {
		Usuario usuarioBD = repo.findById(id_usuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

		usuarioBD.setNombre(usuario.getNombre());
		usuarioBD.setApellido(usuario.getApellido());

		if (!usuarioBD.getCorreo().equals(usuario.getCorreo())) {
			if (repo.existsByCorreo(usuario.getCorreo())) {
				throw new BadRequestException("El correo ya está en uso");
			}
			usuarioBD.setCorreo(usuario.getCorreo());
		}

		if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
			usuarioBD.setPassword(passwordEncoder.encode(usuario.getPassword()));
		}

		if (usuario.getRol() != null && usuario.getRol().getId_rol() != null) {
			Rol nuevoRol = repoRol.findById(usuario.getRol().getId_rol())
					.orElseThrow(() -> new ResourceNotFoundException("Rol no válido"));
			usuarioBD.setRol(nuevoRol);
		}

		if (usuarioLogueado.getRol().getId_rol() == 1) {
			usuarioBD.setEstado(usuario.getEstado() != null ? usuario.getEstado() : usuarioBD.getEstado());
		}

		Usuario actualizado = repo.save(usuarioBD);
		auditoriaService.registrar("tb_usuarios", "UPDATE", "Se actualizó usuario ID: " + actualizado.getId_usuario(), 1);

		return actualizado;
	}

	public void eliminarUsuario(Integer id_usuario, Usuario usuarioLogueado) {
		Usuario usuarioBD = repo.findById(id_usuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

		if (usuarioLogueado.getRol().getId_rol() != 1) {
			if (!usuarioLogueado.getId_usuario().equals(id_usuario)) {
				throw new ForbiddenException("No tienes permiso para eliminar este usuario");
			}
		}

		usuarioBD.setEstado(0);
		repo.save(usuarioBD);
	}

	public void eliminarUsuario(Integer id_usuario) {
		Usuario usuarioBD = repo.findById(id_usuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
		usuarioBD.setEstado(0);
		repo.save(usuarioBD);
		auditoriaService.registrar("tb_usuarios", "DELETE", "Se desactivó usuario ID: " + usuarioBD.getId_usuario(), 1);
	}

	public Usuario login(String correo, String password) {
		Usuario usuario = repo.findByCorreoWithRol(correo)
				.orElseThrow(() -> new BadRequestException("Credenciales inválidas"));

		if (usuario.getEstado() == 0) {
			throw new BadRequestException("Usuario desactivado");
		}

		if (!passwordEncoder.matches(password, usuario.getPassword())) {
			throw new BadRequestException("Credenciales inválidas");
		}

		return usuario;
	}
}
