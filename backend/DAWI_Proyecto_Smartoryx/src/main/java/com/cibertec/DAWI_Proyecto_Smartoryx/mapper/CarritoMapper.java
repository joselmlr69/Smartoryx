package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.CarritoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Carrito;

@Mapper(componentModel = "spring", uses = {CarritoDetalleMapper.class})
public interface CarritoMapper {

    @Mapping(target = "id", source = "id_carrito")
    @Mapping(target = "usuario.id", source = "usuario.id_usuario")
    @Mapping(target = "usuario.nombre", source = "usuario.nombre")
    @Mapping(target = "usuario.apellido", source = "usuario.apellido")
    @Mapping(target = "usuario.correo", source = "usuario.correo")
    @Mapping(target = "detalles", source = "detalles")
    @Mapping(target = "total", ignore = true)
    CarritoResponse toResponse(Carrito carrito);
}
