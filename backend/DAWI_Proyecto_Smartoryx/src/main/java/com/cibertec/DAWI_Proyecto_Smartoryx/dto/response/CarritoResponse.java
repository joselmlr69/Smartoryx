package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponse {
    private Integer id;
    private UsuarioSimpleResponse usuario;
    private List<CarritoDetalleResponse> detalles;
    private Double total;
}
