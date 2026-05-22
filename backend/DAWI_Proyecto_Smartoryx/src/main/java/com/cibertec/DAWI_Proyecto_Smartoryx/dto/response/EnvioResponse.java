package com.cibertec.DAWI_Proyecto_Smartoryx.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnvioResponse {
    private Integer id;
    private String estado;
    private Date fechaEnvio;
}
