package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.CarritoDetalle;

public interface CarritoDetalleRepository extends JpaRepository<CarritoDetalle, Integer> {

    @Query("SELECT cd FROM CarritoDetalle cd WHERE cd.carrito.id_carrito = :idCarrito AND cd.producto.id_producto = :idProducto")
    Optional<CarritoDetalle> findDetalle(@Param("idCarrito") Integer idCarrito,
                                         @Param("idProducto") Integer idProducto);

    @Query("SELECT cd FROM CarritoDetalle cd WHERE cd.carrito.id_carrito = :idCarrito")
    List<CarritoDetalle> listarPorCarrito(@Param("idCarrito") Integer idCarrito);
}