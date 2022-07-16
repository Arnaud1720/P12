package com.arnaud.p12.security;

import com.arnaud.p12.security.jwt.JWTAuthenticationFilter;
import com.arnaud.p12.security.jwt.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.arnaud.asso.p12.security.SecParams.allAccess;
import static com.arnaud.asso.p12.security.SecParams.urlAdmin;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login").permitAll();
        // afficher la liste de tout les utilisateur
        http.authorizeRequests().antMatchers(urlAdmin).hasAuthority("ADMIN");
        //rechercher un association par son id
        http.authorizeRequests().antMatchers(HttpMethod.GET,urlAdmin).hasAnyAuthority("ADMIN","USER","ADHERENT");
        //supprimer une association
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/user/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,allAccess).hasAnyAuthority("","ADMIN","USER","ADHERENT");

        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(new JWTAuthenticationFilter(authenticationManager())) ;
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


}
