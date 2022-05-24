package com.user.profile.worker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author jayensingh
 * <p>
 * <p>
 * 30/04/22
 */
@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/static/**", "/manifest.json", "/swagger-ui.html", "/actuator/**", "/webjars/**",
                        "/swagger-resources/**", "/v2/api-docs", "/h2/**", "/favicon.ico", "/people/**", "/retry/**")
                .permitAll();
    }
}
