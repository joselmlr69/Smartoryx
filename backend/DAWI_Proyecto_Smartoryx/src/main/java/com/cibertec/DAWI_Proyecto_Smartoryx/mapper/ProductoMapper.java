package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.ProductoRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.ProductoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "id", source = "id_producto")
    @Mapping(target = "imagenUrl", source = "imagen_url")
    @Mapping(target = "categoria.id", source = "categoria.id_categoria")
    @Mapping(target = "categoria.nombre", source = "categoria.nombre")
    @Mapping(target = "marca.id", source = "marca.id_marca")
    @Mapping(target = "marca.nombre", source = "marca.nombre")
    @Mapping(target = "proveedor.id", source = "proveedor.id_proveedor")
    @Mapping(target = "proveedor.nombre", source = "proveedor.nombre")
    @Mapping(target = "proveedor.ruc", source = "proveedor.ruc")
    @Mapping(target = "proveedor.telefono", source = "proveedor.telefono")
    ProductoResponse toResponse(Producto producto);

    @Mapping(target = "id_producto", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "marca", ignore = true)
    @Mapping(target = "proveedor", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "imagen_url", source = "imagenUrl")
    Producto toEntity(ProductoRequest request);
}
