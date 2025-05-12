    package it.ecubit.gameshop.security;

    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.CorsConfigurationSource;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
            UserDetails user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("password123"))
                    .roles("USER")
                    .build();
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(user, admin);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/it.ecubit.gameshop/**").permitAll()


                            .anyRequest().permitAll()
                    )
                    .formLogin(login -> login
                            .loginProcessingUrl("/login")
                            .successHandler((request, response, authentication) -> {
                                response.setStatus(HttpServletResponse.SC_OK);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\":\"Login successful\"}");
                            })
                            .failureHandler((request, response, exception) -> {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\":\"Invalid credentials\"}");
                            })
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .permitAll()
                    )
                    .exceptionHandling(e -> e
                            .authenticationEntryPoint((request, response, authException) -> {
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"message\":\"Authentication required\"}");
                            })
                    )
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    );

            return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowCredentials(true);
            configuration.addAllowedOrigin("http://localhost:4200");
            configuration.addAllowedHeader("*");
            configuration.addAllowedMethod("*");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }
    }



