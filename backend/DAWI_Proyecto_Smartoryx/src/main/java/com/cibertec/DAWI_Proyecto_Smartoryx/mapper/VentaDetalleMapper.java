package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.VentaDetalleResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.DetalleVenta;

@Mapper(componentModel = "spring")
public interface VentaDetalleMapper {

    @Mapping(target = "idDetalle", source = "id_detalle")
    @Mapping(target = "producto.id", source = "producto.id_producto")
    @Mapping(target = "producto.nombre", source = "producto.nombre")
    @Mapping(target = "producto.precio", source = "producto.precio")
    @Mapping(target = "producto.imagenUrl", source = "producto.imagen_url")
    VentaDetalleResponse toResponse(DetalleVenta detalle);
}
