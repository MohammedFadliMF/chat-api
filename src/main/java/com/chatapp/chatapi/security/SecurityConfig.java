package com.chatapp.chatapi.security;

// import java.util.Arrays;
// import java.util.Collections;

// import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;

// import jakarta.servlet.http.HttpServletRequest;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http)throws Exception{
        
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
         .authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/signup").permitAll()
        // .authorizeHttpRequests(authorize->authorize.requestMatchers("/api/**").authenticated()
        //    .anyRequest().permitAll())
        .anyRequest().authenticated()
         .and()
        .addFilterAfter(new JwtTokenGeneratorFilter(),BasicAuthenticationFilter.class)
        .addFilterBefore(new JwtTokenValidationFilter(),BasicAuthenticationFilter.class)
        .csrf().disable()
        .formLogin().and().httpBasic();

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
