package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.MetodoPagoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.MetodoPago;

@Mapper(componentModel = "spring")
public interface MetodoPagoMapper {

    @Mapping(target = "id", source = "id_metodo")
    MetodoPagoResponse toResponse(MetodoPago metodoPago);
}
