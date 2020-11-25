package com.yello.routerapp;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;

/**
 * Created by rsundar on 2/3/18.
 */
public class RequestLogFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));


        Enumeration<String> headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()){

            log.info("Request Headers >> " + headerNames.nextElement());
        }


        return null;
    }

}
