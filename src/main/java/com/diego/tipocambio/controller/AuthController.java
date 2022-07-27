package com.diego.tipocambio.controller;

import com.diego.tipocambio.model.CredencialesUsuario;
import com.diego.tipocambio.repository.UsuarioRepository;
import com.diego.tipocambio.security.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthController(UsuarioRepository usuarioRepository, JWTUtil jwtUtil, AuthenticationManager authManager)  {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody CredencialesUsuario body){
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getUsuario(), body.getContrasenha());

        authManager.authenticate(authInputToken);

        return ResponseEntity.ok()
                .body(Collections.singletonMap("jwt-token", jwtUtil.generarToken(body.getUsuario())));
    }

}