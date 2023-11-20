package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 @Override
 protected void  configure(AuthenticationManagerBuilder auth)throws Exception{
     auth
             .inMemoryAuthentication()
             .withUser("admin").
             password(passwordEncoder().encode("admin123")).roles("ADMIN")
             .and()
             .withUser("user")
             .password(passwordEncoder().encode("user123")).roles("USER");
 }

    @Override
    protected void  configure(HttpSecurity http) throws  Exception{
     http
             .authorizeRequests()
             .antMatchers("/api/employees").hasRole("ADMIN")
             .antMatchers("/api/employees/*").hasAnyRole("ADMIN","USER")
             .antMatchers("/api/students").permitAll()
             .anyRequest().authenticated()
             .and()
             .httpBasic();
    }

@Bean
PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
}
}