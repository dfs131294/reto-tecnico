package com.diego.tipocambio.model;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CalcularTipoCambioRequest {

    @NotNull
    private BigDecimal monto;

    @NotNull
    private String monedaOrigen;

    @NotNull
    private String monedaDestino;

    public void toUpperCase(){
        monedaOrigen = monedaOrigen.toUpperCase();
        monedaDestino = monedaDestino.toUpperCase();
    }
}
