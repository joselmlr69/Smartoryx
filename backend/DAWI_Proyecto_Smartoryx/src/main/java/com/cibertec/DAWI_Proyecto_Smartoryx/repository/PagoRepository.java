package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago;

public interface PagoRepository extends JpaRepository<Pago, Integer>{

    @Query("SELECT p FROM Pago p JOIN FETCH p.metodoPago WHERE p.venta.id_venta = :idVenta")
    List<Pago> findByVentaId(@Param("idVenta") Integer idVenta);
}
