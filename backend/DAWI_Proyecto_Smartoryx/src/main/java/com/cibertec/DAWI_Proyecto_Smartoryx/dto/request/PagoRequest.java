package com.cibertec.DAWI_Proyecto_Smartoryx.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequest {

    @NotNull(message = "La venta es obligatoria")
    private Integer idVenta;

    @NotNull(message = "El método de pago es obligatorio")
    private Integer idMetodo;
}
