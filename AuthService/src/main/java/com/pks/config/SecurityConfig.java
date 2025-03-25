package com.pks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    private AuthConfig authConfig;
    private CustomAuthenticationProvider authenticationProvider;
    @Autowired
    public  SecurityConfig(UserDetailsServiceImpl userDetailsService,AuthConfig authConfig,CustomAuthenticationProvider authenticationProvider){
       this.userDetailsService=userDetailsService;
       this.authConfig=authConfig;
        this.authenticationProvider=authenticationProvider;

   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/getMessage").hasRole("ADMIN") // Only ADMIN can access /user/**
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                .httpBasic(); // Enable Basic Authentication
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }



}
