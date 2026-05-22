package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.CategoriaResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Categoria;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "id", source = "id_categoria")
    CategoriaResponse toResponse(Categoria categoria);
}
