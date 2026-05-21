package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Direccion;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {

    @Query("SELECT d FROM Direccion d WHERE d.usuario.id_usuario = :idUsuario")
    List<Direccion> listarPorUsuario(@Param("idUsuario") Integer idUsuario);
}