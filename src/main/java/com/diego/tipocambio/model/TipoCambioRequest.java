package com.diego.tipocambio.model;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TipoCambioRequest {

    @NotNull
    private String monedaOrigen;

    @NotNull
    private String monedaDestino;

    @NotNull
    private BigDecimal tipoCambio;

    public void toUpperCase(){
        monedaOrigen = monedaOrigen.toUpperCase();
        monedaDestino = monedaDestino.toUpperCase();
    }
}
