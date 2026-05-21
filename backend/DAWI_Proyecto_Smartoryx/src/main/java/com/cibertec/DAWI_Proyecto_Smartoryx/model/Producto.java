package com.cibertec.DAWI_Proyecto_Smartoryx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_producto")
	private Integer id_producto;
	@Column(name = "nombre",length = 100)
	private String nombre;
	@Column(name = "descripcion",length = 255)
	private String descripcion;
	private Double precio;
	private Integer stock;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_marca")
	private Marca marca;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	 private Integer estado;
	
	@Column(name = "imagen_url", length = 500)
	private String imagen_url;
	
}
