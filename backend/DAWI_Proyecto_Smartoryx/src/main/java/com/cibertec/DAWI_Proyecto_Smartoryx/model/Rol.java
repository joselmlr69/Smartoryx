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
@Table(name="tb_roles")
public class Rol {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id_rol;
	@Column(name ="nombre",length = 30)
	private String nombre;

}
