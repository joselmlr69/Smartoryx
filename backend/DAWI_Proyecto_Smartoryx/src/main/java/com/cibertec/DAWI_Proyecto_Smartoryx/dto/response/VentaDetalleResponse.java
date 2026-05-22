package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDetalleResponse {
    private Integer idDetalle;
    private ProductoSimpleResponse producto;
    private Integer cantidad;
    private Double precio;
    private Double subtotal;
}
