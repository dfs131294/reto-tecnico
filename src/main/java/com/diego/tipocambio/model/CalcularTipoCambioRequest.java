package com.diego.tipocambio.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CalcularTipoCambioRequest {

    private BigDecimal monto;
    private String monedaOrigen;
    private String monedaDestino;
}
