package com.cibertec.DAWI_Proyecto_Smartoryx.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_auditoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auditoria {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auditoria;

    private String tabla;
    private String accion;
    private String descripcion;

    private Date fecha;

    private Integer id_usuario;

}
