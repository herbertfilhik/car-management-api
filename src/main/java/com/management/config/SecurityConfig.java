package com.management.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**").permitAll() // Permite o acesso ao Swagger UI e recursos relacionados
            .requestMatchers("/cars/**").hasRole("ADMIN") // Exemplo: apenas usuários com o papel 'ADMIN' podem acessar "/cars/**"
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // Permite o acesso a recursos estáticos
            .anyRequest().authenticated() // Todas as outras requisições precisam de autenticação
            .and()
            .formLogin() // Habilita o formulário de login
            .and()
            .logout(); // Habilita o logout

        return http.build();
    }
}
