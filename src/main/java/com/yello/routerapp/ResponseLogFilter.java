package com.yello.routerapp;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by rsundar on 2/3/18.
 */
public class ResponseLogFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ResponseLogFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse servletResponse = context.getResponse();

        Collection<String> headerNames = servletResponse.getHeaderNames();

        Integer responsecode = servletResponse.getStatus();

        log.info(String.format("Response is %s", Integer.toString(responsecode)));

        for (String headerName : headerNames) {
            Collection<String> values = servletResponse.getHeaders(headerName);
            log.info("  Header {}: {}", headerName, values);
        }
        return null;
    }
}
