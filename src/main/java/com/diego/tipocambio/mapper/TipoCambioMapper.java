package com.diego.tipocambio.mapper;

import com.diego.tipocambio.model.TipoCambioRequest;
import com.diego.tipocambio.model.TipoCambio;
import com.diego.tipocambio.model.TipoCambioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TipoCambioMapper {

    TipoCambioMapper MAPPER = Mappers.getMapper(TipoCambioMapper.class);

    TipoCambio dtoToModel(TipoCambioRequest tipoCambioRequest);
    TipoCambioResponse modelToDto(TipoCambio tipoCambio);
}
