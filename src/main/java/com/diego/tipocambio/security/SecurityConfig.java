package com.diego.tipocambio.security;

import com.diego.tipocambio.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;
    private final JWTFilter filter;

    private final DetalleUsuarioService detalleUsuarioService;

    public SecurityConfig(UsuarioRepository usuarioRepository, JWTFilter filter, DetalleUsuarioService detalleUsuarioService)  {
        this.usuarioRepository = usuarioRepository;
        this.filter = filter;
        this.detalleUsuarioService = detalleUsuarioService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .httpBasic().disable()
            .cors()
            .and()
            .authorizeHttpRequests()
            .antMatchers("/auth/**", "/h2/**").permitAll()
            .antMatchers("/tipo-cambio/**").hasRole("ADMIN")
            .and()
            .userDetailsService(detalleUsuarioService)
            .exceptionHandling()
            .authenticationEntryPoint(
                    (request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No está autorizado para ejecutar este endpoint")
            )
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers().frameOptions().disable();
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}