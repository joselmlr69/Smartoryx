package com.cibertec.DAWI_Proyecto_Smartoryx.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_proveedores")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_proveedor;
	@Column(name = "nombre",length = 100)
	private String nombre;
	@Column(name = "ruc", length = 11)
	private String ruc;
	@Column(name="telefono", length = 20)
	private String telefono;

}
