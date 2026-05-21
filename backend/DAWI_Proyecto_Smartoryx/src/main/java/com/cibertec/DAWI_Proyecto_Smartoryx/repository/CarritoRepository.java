package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

	@Query("SELECT c FROM Carrito c WHERE c.usuario.id_usuario = :idUsuario")
	Optional<Carrito> findCarritoByUsuario(@Param("idUsuario") Integer idUsuario);
	
}