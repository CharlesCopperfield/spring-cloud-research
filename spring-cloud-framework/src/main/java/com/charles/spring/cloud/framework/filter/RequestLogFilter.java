package com.charles.spring.cloud.framework.filter;

import com.charles.spring.cloud.framework.constants.ApplicationNameAndPathConstants;
import com.charles.spring.cloud.framework.utils.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: st-wgsf-zengs
 * @time: 12/4/2019 1:26 PM
 */
@Configuration
@WebFilter(filterName = "requestLogFilter", urlPatterns = "/*")
public class RequestLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        // 记录内部service调用
        long start = System.currentTimeMillis();
        boolean isInnerService = requestURI.contains(ApplicationNameAndPathConstants.INNER_SERVICE_PATH_SUFFIX);
        if (isInnerService) {
            logger.info(RequestUtils.getRequestMessage(request));
        }

        filterChain.doFilter(request, servletResponse);

        if (isInnerService) {
            logger.info(getResponseLog((HttpServletResponse) servletResponse, start));
        }
    }

    private String getResponseLog(HttpServletResponse servletResponse, long start) {
        HttpServletResponse httpServletResponse = servletResponse;
        int status = httpServletResponse.getStatus();
        return "Completed " + status + ", cost " + (System.currentTimeMillis() - start) + " ms.";
    }


}