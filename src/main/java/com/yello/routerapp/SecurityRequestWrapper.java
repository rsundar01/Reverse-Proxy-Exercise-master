package com.yello.routerapp;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by rsundar on 2/4/18.
 */

public class SecurityRequestWrapper extends HttpServletRequestWrapper {



    public SecurityRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);

        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = InjectionFilter.dangerousParamStripandEncode(values[i], true);
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);

        return InjectionFilter.dangerousParamStripandEncode(value, true);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return InjectionFilter.dangerousParamStripandEncode(value, false);
    }


}