package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.VentaResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Venta;

@Mapper(componentModel = "spring", uses = {VentaDetalleMapper.class})
public interface VentaMapper {

    @Mapping(target = "id", source = "id_venta")
    @Mapping(target = "usuario.id", source = "usuario.id_usuario")
    @Mapping(target = "usuario.nombre", source = "usuario.nombre")
    @Mapping(target = "usuario.apellido", source = "usuario.apellido")
    @Mapping(target = "usuario.correo", source = "usuario.correo")
    @Mapping(target = "detalles", source = "detalles")
    VentaResponse toResponse(Venta venta);

    List<VentaResponse> toResponseList(List<Venta> ventas);
}
