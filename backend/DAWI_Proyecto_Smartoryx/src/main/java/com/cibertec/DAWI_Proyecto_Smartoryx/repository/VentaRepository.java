package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT v FROM Venta v JOIN FETCH v.usuario WHERE v.usuario.id_usuario = :idUsuario ORDER BY v.fecha DESC")
    List<Venta> findByUsuarioId(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT v FROM Venta v JOIN FETCH v.usuario JOIN FETCH v.detalles dv JOIN FETCH dv.producto WHERE v.id_venta = :idVenta")
    java.util.Optional<Venta> findByIdWithDetalles(@Param("idVenta") Integer idVenta);
}
