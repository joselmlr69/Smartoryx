package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.MarcaResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Marca;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    @Mapping(target = "id", source = "id_marca")
    MarcaResponse toResponse(Marca marca);
}
