package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u JOIN FETCH u.rol") 
	List<Usuario> listarUsuarios();
	
	@Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.correo = :correo")
	boolean existsByCorreo(@Param("correo") String correo);
	
	Usuario findByCorreoAndPassword(String correo, String password);
}

