package com.charles.spring.cloud.framework.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.WebUtils;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: st-wgsf-zengs
 * @time: 12/4/2019 2:45 PM
 */
public abstract class RequestUtils {

    /**
     * @return 类似GET "/user/queryAll", parameters={}格式
     * @see DispatcherServlet#logRequest
     */
    public static String getRequestMessage(HttpServletRequest request) {
        String params = request.getParameterMap().entrySet().stream()
                .map(entry -> entry.getKey() + ":" + Arrays.toString(entry.getValue()))
                .collect(Collectors.joining(", "));

        String queryString = request.getQueryString();
        String queryClause = (StringUtils.hasLength(queryString) ? "?" + queryString : "");
        String dispatchType = (!request.getDispatcherType().equals(DispatcherType.REQUEST) ?
                "\"" + request.getDispatcherType().name() + "\" dispatch for " : "");
        String message = (dispatchType + request.getMethod() + " \"" + getRequestUri(request) +
                queryClause + "\", parameters={" + params + "}");
        return message;
    }

    private static String getRequestUri(HttpServletRequest request) {
        String uri = (String) request.getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE);
        if (uri == null) {
            uri = request.getRequestURI();
        }
        return uri;
    }
}
