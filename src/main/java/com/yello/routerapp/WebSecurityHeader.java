package com.yello.routerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by rsundar on 2/3/18.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityHeader extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable()
                .contentSecurityPolicy("script-src 'self'; object-src 'self'; img-src 'self'; report-uri https://4f73336d02452d11d0cd3e563b986e5c.report-uri.com/r/d/csp/reportOnly");

    }


}
