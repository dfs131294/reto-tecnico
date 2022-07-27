package com.diego.tipocambio.repository;

import com.diego.tipocambio.model.TipoCambio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoCambioRepository extends CrudRepository<TipoCambio, Long> {

    Optional<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);

    List<TipoCambio> findAll();
}
