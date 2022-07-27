package com.diego.tipocambio.controller;

import com.diego.tipocambio.model.*;
import com.diego.tipocambio.service.TipoCambioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tipo-cambio")
public class TipoCambioController {

    private final TipoCambioService tipoCambioService;

    public TipoCambioController(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @GetMapping()
    public ResponseEntity<List<TipoCambio>> listar() {
        return ResponseEntity.ok().body(tipoCambioService.listar());
    }

    @PostMapping("/calcular")
    public ResponseEntity<CalcularTipoCambioResponse> calcular(@RequestBody CalcularTipoCambioRequest tipoCambioRequest) {
        return ResponseEntity.ok().body(tipoCambioService.calcular(tipoCambioRequest));
    }

    @PostMapping()
    public ResponseEntity<TipoCambioResponse> guardar(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return ResponseEntity.ok().body(tipoCambioService.guardar(tipoCambioRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoCambioResponse> actualizar(@RequestBody TipoCambioRequest tipoCambioRequest, @PathVariable Long id) {
        return ResponseEntity.ok().body(tipoCambioService.actualizar(tipoCambioRequest, id));
    }

    @PostMapping("/actualizar")
    public ResponseEntity<TipoCambioResponse> actualizarTipoCambio(@RequestBody TipoCambioRequest tipoCambioRequest) {
        return ResponseEntity.ok().body(tipoCambioService.actualizarTipoCambio(tipoCambioRequest));
    }
}
