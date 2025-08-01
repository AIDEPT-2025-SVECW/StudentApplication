package com.bvraju.aidept.StudentApplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bvraju.aidept.StudentApplication.Service.impl.MyUserDetailsService;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user1 = User.withDefaultPasswordEncoder()
    // .username("student")
    // .password("password")
    // .roles("USER")
    // .build();

    // UserDetails admin = User.withDefaultPasswordEncoder()
    // .username("admin")
    // .password("admin123")
    // .roles("ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(user1, admin);
    // }
    @Autowired
    private MyUserDetailsService myUserService;

    // @Bean
    // public AuthenticationProvider getAuthenticationProvider() {
    // DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
    // daoProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    // daoProvider.setUserDetailsService(myUserService);
    // return daoProvider;
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .requestMatchers("/students").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                        .requestMatchers("/skills").hasAnyRole("ADMIN", "STUDENT", "TEACHER", "GUEST")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .logout(Customizer.withDefaults())

                .build();
    }

}
