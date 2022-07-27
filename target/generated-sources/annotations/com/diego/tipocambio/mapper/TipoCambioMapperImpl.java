package com.diego.tipocambio.mapper;

import com.diego.tipocambio.model.TipoCambio;
import com.diego.tipocambio.model.TipoCambioRequest;
import com.diego.tipocambio.model.TipoCambioResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-27T12:45:44-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class TipoCambioMapperImpl implements TipoCambioMapper {

    @Override
    public TipoCambio dtoToModel(TipoCambioRequest tipoCambioRequest) {
        if ( tipoCambioRequest == null ) {
            return null;
        }

        TipoCambio tipoCambio = new TipoCambio();

        tipoCambio.setMonedaOrigen( tipoCambioRequest.getMonedaOrigen() );
        tipoCambio.setMonedaDestino( tipoCambioRequest.getMonedaDestino() );
        tipoCambio.setTipoCambio( tipoCambioRequest.getTipoCambio() );

        return tipoCambio;
    }

    @Override
    public TipoCambioResponse modelToDto(TipoCambio tipoCambio) {
        if ( tipoCambio == null ) {
            return null;
        }

        TipoCambioResponse.TipoCambioResponseBuilder tipoCambioResponse = TipoCambioResponse.builder();

        tipoCambioResponse.monedaOrigen( tipoCambio.getMonedaOrigen() );
        tipoCambioResponse.monedaDestino( tipoCambio.getMonedaDestino() );
        tipoCambioResponse.tipoCambio( tipoCambio.getTipoCambio() );

        return tipoCambioResponse.build();
    }
}
