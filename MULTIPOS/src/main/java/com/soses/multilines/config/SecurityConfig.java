package com.soses.multilines.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.soses.multilines.auth.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http
            .authorizeHttpRequests(requests -> requests
	            .requestMatchers("/favicon.ico").permitAll()
	            .requestMatchers("/css/public/**").permitAll()
	            .requestMatchers("/js/public/**").permitAll())
            .formLogin(login -> login.loginPage("/login").permitAll())
            .logout(logout -> logout.permitAll());

        //http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
        
//        List<AntMatcher> matchers = antMatcherRepo.findAllByEndDateGreaterThanAndEffDateLessThanEqual(LocalDate.now(), LocalDate.now());
//        for (AntMatcher matcher : matchers) {
//        	http.authorizeHttpRequests().antMatchers(matcher.getPath()).access(matcher.getRoleInfo());
//        }
        
        http
	        .authorizeHttpRequests(requests -> requests
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated())
        ;

        return http.build();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
	
	
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
