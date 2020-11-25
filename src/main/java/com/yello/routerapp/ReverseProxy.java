package com.yello.routerapp;

import com.google.common.annotations.Beta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * Created by rsundar on 2/3/18.
 */
@EnableZuulProxy
@SpringBootApplication
public class ReverseProxy {


    public static void main(String[] args) {
        SpringApplication.run(ReverseProxy.class, args);
    }


    @Bean
    public RequestLogFilter requestLogFilter() {
        return new RequestLogFilter();
    }

    @Bean
    public ResponseLogFilter responseLogFilter(){
        return new ResponseLogFilter();
    }

    @Bean
    public SecurityFilter securityFilter(){
        return new SecurityFilter();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter();
    }
}
