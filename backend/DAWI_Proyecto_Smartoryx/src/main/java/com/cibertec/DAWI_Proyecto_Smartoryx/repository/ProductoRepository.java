package com.cibertec.DAWI_Proyecto_Smartoryx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

	@Query("SELECT p FROM Producto p " + "JOIN FETCH p.categoria " + "JOIN FETCH p.marca " + "JOIN FETCH p.proveedor ORDER BY p.id_producto ASC")
	List<Producto> listarCompleto();

	@Query(value = "SELECT p FROM Producto p JOIN FETCH p.categoria JOIN FETCH p.marca JOIN FETCH p.proveedor ORDER BY p.id_producto ASC",
	       countQuery = "SELECT COUNT(p) FROM Producto p")
	org.springframework.data.domain.Page<Producto> findAllPaged(org.springframework.data.domain.Pageable pageable);

	@Query("SELECT p FROM Producto p JOIN FETCH p.categoria JOIN FETCH p.marca JOIN FETCH p.proveedor WHERE p.marca.id_marca = :idMarca AND p.estado = 1")
	List<Producto> findByMarcaAndActivo(@Param("idMarca") Integer idMarca);

	@Query("SELECT p FROM Producto p JOIN FETCH p.categoria JOIN FETCH p.marca JOIN FETCH p.proveedor WHERE p.estado = 1")
	List<Producto> findAllActivos();

	@Query("SELECT p FROM Producto p JOIN FETCH p.categoria JOIN FETCH p.marca JOIN FETCH p.proveedor WHERE p.id_producto = :id AND p.estado = 1")
	java.util.Optional<Producto> findByIdActivo(@Param("id") Integer id);

	@Query("SELECT p FROM Producto p JOIN FETCH p.categoria JOIN FETCH p.marca JOIN FETCH p.proveedor WHERE p.categoria.id_categoria = :idCategoria AND p.estado = 1")
	List<Producto> findByCategoriaIdCategoriaAndEstado(@Param("idCategoria") Integer idCategoria);
}
