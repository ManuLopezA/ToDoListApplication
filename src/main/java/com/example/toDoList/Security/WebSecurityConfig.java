package com.example.toDoList.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.toDoList.Models.CustomAuthenticationSuccessHandler;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	// This bean can be removed if you choose not to use a custom success handler.
    @Bean 
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }
	 
    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .requestMatchers("/", "/index", "/filter-todos", "/login", "/static/**")
                .permitAll()
                .requestMatchers("/create-todo", "/save-todo", "/edit-todo", "/update-todo")
                .authenticated()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login") 
                .permitAll()
             // .defaultSuccessUrl("/") // Uncomment this line if not using CustomAuthenticationSuccessHandler
                .failureUrl("/login-error?loginError=true")
                .successHandler(customAuthenticationSuccessHandler())
            .and()
            // no me está funcionando el logout
            .logout()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout=true")
	            .invalidateHttpSession(true)
	            .permitAll()
	        .and()
	            .exceptionHandling()
	                .accessDeniedPage("/login");
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
    	return NoOpPasswordEncoder.getInstance(); // developement
    }
}
