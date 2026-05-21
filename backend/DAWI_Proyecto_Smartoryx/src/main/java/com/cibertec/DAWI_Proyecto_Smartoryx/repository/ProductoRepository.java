package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	@Query("SELECT p FROM Producto p " + "JOIN FETCH p.categoria " + "JOIN FETCH p.marca " + "JOIN FETCH p.proveedor")
	    List<Producto> listarCompleto();
	
}
