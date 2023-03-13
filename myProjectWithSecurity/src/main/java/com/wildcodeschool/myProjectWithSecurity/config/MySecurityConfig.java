package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> {
                    try {
                        authorize
                                .requestMatchers("/secret-bases/**").hasRole("DIRECTOR")
                                .requestMatchers("/avengers/assemble/**").hasAnyRole("CHAMPION", "DIRECTOR")
                                .anyRequest().authenticated();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .formLogin()
                .and().httpBasic()
                .and().logout().logoutSuccessUrl("/")
                .and().exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.sendRedirect("/access-denied");
                });

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager configureUser() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User
                .withUsername("Steve")
                .password(encoder.encode("motdepasse"))
                .roles("CHAMPION")
                .build();

        UserDetails admin = User
                .withUsername("Nick")
                .password(encoder.encode("flerken"))
                .roles("DIRECTOR")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
