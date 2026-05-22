package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.request.UsuarioRequest;
import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.UsuarioResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", source = "id_usuario")
    @Mapping(target = "rol.id", source = "rol.id_rol")
    @Mapping(target = "rol.nombre", source = "rol.nombre")
    UsuarioResponse toResponse(Usuario usuario);

    @Mapping(target = "id_usuario", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Usuario toEntity(UsuarioRequest request);
}
