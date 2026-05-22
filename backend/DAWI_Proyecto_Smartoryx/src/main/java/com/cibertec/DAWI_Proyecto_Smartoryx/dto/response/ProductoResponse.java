package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponse {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Integer estado;
    private String imagenUrl;
    private CategoriaResponse categoria;
    private MarcaResponse marca;
    private ProveedorResponse proveedor;
}
