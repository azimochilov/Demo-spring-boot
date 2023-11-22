package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 private final UserDetailsService userDetailsService;

    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

  /*  @Override
 protected void  configure(AuthenticationManagerBuilder auth)throws Exception{
     auth
             .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }
*/
    @Override
    protected void  configure(HttpSecurity http) throws  Exception{
     http
             .csrf()
             .disable()
             .headers()
             .frameOptions()
             .disable()
             .and()
             .authorizeRequests()
             .antMatchers("/api/employees").hasRole("ADMIN")
             .antMatchers("/api/employees/*").hasAnyRole("ADMIN","USER")
             .antMatchers("/api/students").permitAll()
             .antMatchers("/api/register").permitAll()
             .anyRequest().authenticated()
             .and()
             .httpBasic();
    }
/*
@Bean
PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
}*/
}