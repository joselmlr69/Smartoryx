package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoSimpleResponse {
    private Integer id;
    private String nombre;
    private Double precio;
    private String imagenUrl;
}
