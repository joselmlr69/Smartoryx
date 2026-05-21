package com.cibertec.DAWI_Proyecto_Smartoryx.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_ventas")
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venta")
	private Integer id_venta;
	@Column(name = "fecha")
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@Column(name = "total")
	private Double total;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<DetalleVenta> detalles;
	@Column(name = "estado")
	private String estado ="PENDIENTE";
}
