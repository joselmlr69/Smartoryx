package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponse {
    private Integer id;
    private Date fecha;
    private Double total;
    private String estado;
    private UsuarioSimpleResponse usuario;
    private List<VentaDetalleResponse> detalles;
}
