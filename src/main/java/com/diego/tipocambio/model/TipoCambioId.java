package com.diego.tipocambio.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TipoCambioId implements Serializable {

    private String monedaOrigen;
    private String monedaDestino;
}
