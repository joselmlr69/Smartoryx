package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.RolResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "id", source = "id_rol")
    RolResponse toResponse(Rol rol);
}
