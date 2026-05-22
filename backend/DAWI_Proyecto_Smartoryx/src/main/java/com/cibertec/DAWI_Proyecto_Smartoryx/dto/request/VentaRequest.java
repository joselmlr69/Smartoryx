package com.cibertec.DAWI_Proyecto_Smartoryx.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaRequest {

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    @NotEmpty(message = "La venta debe tener al menos un detalle")
    @Valid
    private List<VentaDetalleRequest> detalles;
}
