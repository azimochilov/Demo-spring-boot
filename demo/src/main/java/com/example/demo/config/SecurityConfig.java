package com.example.demo.config;

import com.example.demo.security.JwtConfigurer;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 private final UserDetailsService userDetailsService;

 private final JwtTokenProvider jwtTokenProvider;
    public SecurityConfig(@Lazy UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

  /*  @Override
 protected void  configure(AuthenticationManagerBuilder auth)throws Exception{
     auth
             .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }
*/

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return  super.authenticationManager();
    }
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
             .antMatchers("/api/register").permitAll()
             .antMatchers("/api/login").permitAll()
             .antMatchers("/api/employees").hasRole("ADMIN")
             .antMatchers("/api/employees/*").hasAnyRole("ADMIN","USER")
             .antMatchers("/api/students").permitAll()
             .anyRequest().authenticated()
             .and()
             .apply(new JwtConfigurer(jwtTokenProvider));
    }
/*
@Bean
PasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
}*/
}