package com.bvraju.aidept.StudentApplication.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.bvraju.aidept.StudentApplication.Service.impl.MyUserDetailsService;
import com.mysql.cj.protocol.AuthenticationProvider;

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

    // @Bean
    // public SecurityFilterChain getCustomizedFilterChain(HttpSecurity
    // httpSecurity) {
    // SecurityFilterChain existingOne = httpSecurity.getOrBuild();
    // if (existingOne instanceof DefaultSecurityFilterChain defaultChain) {
    // System.out.println("🔍 Filters in this chain:");
    // defaultChain.getFilters().forEach(filter -> System.out.println("➡️ " +
    // filter.getClass().getSimpleName()));
    // }
    // return existingOne;
    // }

}
