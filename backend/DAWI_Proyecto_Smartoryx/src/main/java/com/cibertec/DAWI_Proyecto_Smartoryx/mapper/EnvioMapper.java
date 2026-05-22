package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.EnvioResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Envio;

@Mapper(componentModel = "spring")
public interface EnvioMapper {

    @Mapping(target = "id", source = "id_envio")
    EnvioResponse toResponse(Envio envio);
}
