package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.CarritoDetalleResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.CarritoDetalle;

@Mapper(componentModel = "spring")
public interface CarritoDetalleMapper {

    @Mapping(target = "idDetalle", source = "id_detalle")
    @Mapping(target = "producto.id", source = "producto.id_producto")
    @Mapping(target = "producto.nombre", source = "producto.nombre")
    @Mapping(target = "producto.precio", source = "producto.precio")
    @Mapping(target = "producto.imagenUrl", source = "producto.imagen_url")
    @Mapping(target = "subtotal", expression = "java(detalle.getProducto().getPrecio() * detalle.getCantidad())")
    CarritoDetalleResponse toResponse(CarritoDetalle detalle);
}
