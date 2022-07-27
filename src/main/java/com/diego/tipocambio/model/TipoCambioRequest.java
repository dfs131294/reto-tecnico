package com.diego.tipocambio.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TipoCambioRequest {

    private String monedaOrigen;
    private String monedaDestino;
    private BigDecimal tipoCambio;
}
