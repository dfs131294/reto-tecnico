package com.diego.tipocambio.security;


import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final DetalleUsuarioService detalleUsuarioService;
    private final JWTUtil jwtUtil;

    private static final String MENSAJE_TOKEN_INVALIDO = "JWT Token inválido";

    public JWTFilter(DetalleUsuarioService detalleUsuarioService, JWTUtil jwtUtil) {
        this.detalleUsuarioService = detalleUsuarioService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);

            if (!StringUtils.hasText(jwt)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, MENSAJE_TOKEN_INVALIDO);
            } else {
                try{
                    String usuario = jwtUtil.validarTokenYObtenerUsuario(jwt);
                    UserDetails userDetails = detalleUsuarioService.loadUserByUsername(usuario);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(usuario, userDetails.getPassword(), userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch (JWTVerificationException ex) {
                    log.error(ex.getMessage());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}