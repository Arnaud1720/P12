package com.arnaud.p12.security;

import com.arnaud.p12.security.jwt.JWTAuthenticationFilter;
import com.arnaud.p12.security.jwt.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin("*")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  {

    private final UserDetailsService userDetailsService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(
            BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/**/association/save/**/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"**/**/association/all").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/**/association/save/**/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/**/**/user/save").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/**/user/all").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/**/user/search").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/**/user/{id}").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/**/user/{id}").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(new JWTAuthenticationFilter(authenticationManager())) ;
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


}
