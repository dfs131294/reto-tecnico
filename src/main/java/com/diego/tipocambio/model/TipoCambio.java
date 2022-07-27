package com.diego.tipocambio.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(TipoCambioId.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TipoCambio {

    @Id
    @Column(nullable = false)
    private String monedaOrigen;

    @Id
    @Column(nullable = false)
    private String monedaDestino;

    @Column(nullable = false)
    private BigDecimal tipoCambio;
}
