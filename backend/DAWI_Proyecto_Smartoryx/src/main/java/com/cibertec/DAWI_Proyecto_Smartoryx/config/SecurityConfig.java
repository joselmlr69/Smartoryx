package com.cibertec.DAWI_Proyecto_Smartoryx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/producto/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/marca/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/categoria/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/metodo-pago/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/rol/**").permitAll()
                .requestMatchers("/usuario/login").permitAll()
                .requestMatchers("/usuario/agregar").permitAll()
                .requestMatchers("/usuario/sesion").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers(HttpMethod.POST, "/producto/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/producto/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/producto/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/usuario/listar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/usuario/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/categoria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/categoria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categoria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/marca/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/marca/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/proveedor/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/proveedor/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/proveedor/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/envio/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/envio/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/envio/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/envio/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/auditoria/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/movimiento-stock/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4321"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
