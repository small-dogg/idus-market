package com.idus.market.config.security;

//import com.idus.market.config.jwt.JwtAuthenticationFilter;

import com.idus.market.config.jwt.JwtAuthenticationFilter;
import com.idus.market.config.jwt.TokenProvider;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final CorsFilter corsFilter;
  private final TokenProvider tokenProvider;

  @Bean
  public DelegatingPasswordEncoder passwordEncoder() {
    String idForEncode = "bcrypt";
    HashMap<String, PasswordEncoder> encoderHashMap = new HashMap<>();
    encoderHashMap.put("bcrypt", new BCryptPasswordEncoder());
    encoderHashMap.put("pbkdf2", new Pbkdf2PasswordEncoder());
    encoderHashMap.put("scrypt", new SCryptPasswordEncoder());
    return new DelegatingPasswordEncoder(idForEncode, encoderHashMap);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(corsFilter)
        .formLogin().disable()
        .httpBasic().disable()
        .addFilterBefore(new JwtAuthenticationFilter(tokenProvider),
            UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers("/api/**/auth/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/**/orders").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/api/**/orders").hasAnyRole("ADMIN", "USER")
        .antMatchers("/api/**/user", "/api/**/user/**").hasRole("ADMIN")
        .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**",
            "/swagger/**").permitAll()
        .anyRequest().permitAll();
  }
}
