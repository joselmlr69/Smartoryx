package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u JOIN FETCH u.rol") 
	List<Usuario> listarUsuarios();
	
	@Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.correo = :correo")
	boolean existsByCorreo(@Param("correo") String correo);
	
	Optional<Usuario> findByCorreo(String correo);
	
	@Query("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.correo = :correo")
	Optional<Usuario> findByCorreoWithRol(@Param("correo") String correo);
}

