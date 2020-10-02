package com.luizalabs.information.address.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.luizalabs.information.address.api.security.DefaultAuthenticationEntryPoint;
import com.luizalabs.information.address.api.security.JwtConfig;
import com.luizalabs.information.address.api.security.JwtTokenAuthenticationFilter;

import lombok.Generated;

@Configuration
@EnableWebSecurity
@Profile("!test")
@Generated
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(new DefaultAuthenticationEntryPoint()).and()
                .authorizeRequests().antMatchers("/actuator/health", "/actuator/info",
                "/swagger-resources/**", "/v2/api-docs/**", "/csrf/**",
                "/webjars/**", "/swagger-ui.html")
                .permitAll().anyRequest().authenticated();
        http.addFilter(new JwtTokenAuthenticationFilter(authenticationManager(), jwtConfig));

    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}
