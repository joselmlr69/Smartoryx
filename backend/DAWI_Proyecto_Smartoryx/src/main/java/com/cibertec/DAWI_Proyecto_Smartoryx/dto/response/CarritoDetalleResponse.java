package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDetalleResponse {
    private Integer idDetalle;
    private ProductoSimpleResponse producto;
    private Integer cantidad;
    private Double subtotal;
}
