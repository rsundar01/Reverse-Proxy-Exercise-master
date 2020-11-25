package com.yello.routerapp;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rsundar on 2/3/18.
 */



public class TokenAuthenticationFilter extends ZuulFilter{


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null ||  !authenticate(authorizationHeader)){
            ctx.unset();
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }

    private boolean authenticate(String token){

        // Just going to do a simple token comparison
        boolean authresult = false;

        if(token.contains("PhqZh+iyZ5oo6MY+HEXtyusxxhucG/Il1aQvo1woYkc=")){
            authresult = true;
        }

        return authresult;
    }
}

