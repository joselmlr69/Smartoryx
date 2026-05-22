package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionResponse {
    private Integer id;
    private String direccion;
    private String ciudad;
    private String referencia;
}
