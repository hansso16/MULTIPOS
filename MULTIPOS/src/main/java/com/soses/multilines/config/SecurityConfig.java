package com.soses.multilines.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
            .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/favicon.ico").permitAll()
	            .requestMatchers("/css/public/**").permitAll()
	            .requestMatchers("/js/public/**").permitAll()
	            .requestMatchers("/").authenticated()
	            .anyRequest().authenticated()
            )
            .formLogin(login -> login
        		.loginPage("/login")
        		.permitAll()
        		.defaultSuccessUrl("/", true)
    		)
            .logout(logout -> logout.permitAll());

        return http.build();
    }
    
    @Bean
    AuthenticationManager authManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
