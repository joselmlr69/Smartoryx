package com.cibertec.DAWI_Proyecto_Smartoryx.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cibertec.DAWI_Proyecto_Smartoryx.dto.response.PagoResponse;
import com.cibertec.DAWI_Proyecto_Smartoryx.model.Pago;

@Mapper(componentModel = "spring", uses = {MetodoPagoMapper.class})
public interface PagoMapper {

    @Mapping(target = "id", source = "id_pago")
    @Mapping(target = "metodoPago", source = "metodoPago")
    PagoResponse toResponse(Pago pago);
}
