package com.diego.tipocambio.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    private final static String SUBJECT = "Detalle de Usuario";
    private final static String CLAIM = "usuario";
    private final static String ISSUER = "TIPO-CAMBIO-API";
    private final static Integer TIEMPO_EXPIRACION_SEG = 300;

    public String generarToken(String usuario) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
            .withSubject(SUBJECT)
            .withClaim(CLAIM, usuario)
            .withIssuedAt(Instant.now())
            .withIssuer(ISSUER)
            .withExpiresAt(Instant.now()
                    .plusSeconds(TIEMPO_EXPIRACION_SEG))
            .sign(Algorithm.HMAC256(secret));
    }

    public String validarTokenYObtenerUsuario(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(CLAIM).asString();
    }
}
