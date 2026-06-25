package com.module1.springbootdemo.h5.config;

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
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/student/fetchingAllStudentsNplusoneProblem").permitAll()   // these routes public others need login id and pass
                                .requestMatchers("/api/audit/**").hasAnyRole("ADMIN", "OWNER")
                                .anyRequest().authenticated())   // any request except request matchers should need id and pass
                                .csrf(csrfConfig -> csrfConfig.disable()) // if we want to disable csrf token, by default it is enable
//                                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // if we need to disable session, we only need to disable both csrf and session in case of JWT when we are using
                                .formLogin(Customizer.withDefaults());    // default with form session id

        return http.build();
    }

    @Bean
    UserDetailsService inMemoryUserDetailsService() {

        UserDetails normalUser = User.withUsername("tanish")
                .password(passwordEncoder().encode("pass123"))
                .roles("USER")
                .build();

        UserDetails adminUser = User.withUsername("tanishdahiya")
                .password(passwordEncoder().encode("pass1234"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
