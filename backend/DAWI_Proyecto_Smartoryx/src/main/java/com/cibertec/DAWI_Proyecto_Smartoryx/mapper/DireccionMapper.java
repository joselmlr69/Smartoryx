package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.DireccionResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Direccion;

@Mapper(componentModel = "spring")
public interface DireccionMapper {

    @Mapping(target = "id", source = "id_direccion")
    DireccionResponse toResponse(Direccion direccion);
}
