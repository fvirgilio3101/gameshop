package it.ecubit.gameshop.security;

import it.ecubit.gameshop.service.GSUserDetailService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired

    private GSUserDetailService gsUserDetailService;

    @Autowired

    private JwtAuthFilter jwtAuthFilter;

    @Bean

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()

                        .requestMatchers("/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/videogame/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/genre/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/index/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/videogame/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/videogame/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/videogame/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/genre/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/rating/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("USER")
                        .anyRequest().authenticated()

                )

                .exceptionHandling(ex -> ex

                        .authenticationEntryPoint((request, response, authException) -> {

                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                            response.setContentType("application/json");

                            response.getWriter().write("{\"message\":\"Authentication required\"}");

                        })

                );

        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean

    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(gsUserDetailService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;

    }

    @Bean

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean

    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        config.addAllowedOriginPattern("http://localhost:4200");

        config.addAllowedHeader("*");

        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;

    }

}

