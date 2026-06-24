package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer estado;
    private RolResponse rol;
}
