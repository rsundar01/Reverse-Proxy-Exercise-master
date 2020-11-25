package com.yello.routerapp;

import com.google.common.collect.Maps;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by rsundar on 2/4/18.
 */


public class SecurityFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
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
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Map<String, List<String>> params = ctx.getRequestQueryParams();

        if (params == null) {
            params = Maps.newHashMap();
        } else {

            Iterator iterator =  params.entrySet().iterator();

            while(iterator.hasNext()){

                Map.Entry mapitem = (Map.Entry) iterator.next();

                List<String> valuelist = (List<String>) mapitem.getValue();

                for(int listiter = 0; listiter < valuelist.size(); listiter++){
                    valuelist.set(listiter, InjectionFilter.dangerousParamStripandEncode(valuelist.get(listiter), true));
                }

                params.put((String) mapitem.getKey(), valuelist);
            }
        }


        ctx.setRequestQueryParams(params);

        return null;
    }



}