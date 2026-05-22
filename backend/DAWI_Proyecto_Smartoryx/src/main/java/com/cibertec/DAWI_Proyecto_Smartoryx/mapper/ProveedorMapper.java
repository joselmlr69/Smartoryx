package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.ProveedorResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Proveedor;

@Mapper(componentModel = "spring")
public interface ProveedorMapper {

    @Mapping(target = "id", source = "id_proveedor")
    ProveedorResponse toResponse(Proveedor proveedor);
}
