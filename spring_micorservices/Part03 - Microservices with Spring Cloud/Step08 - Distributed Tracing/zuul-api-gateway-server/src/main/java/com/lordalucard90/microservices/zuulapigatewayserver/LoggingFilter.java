package com.lordalucard90.microservices.zuulapigatewayserver;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter extends ZuulFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        /*
        * "pre"
        * "post"
        * "error"
        * */
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1; // filter priority
    }

    @Override
    public boolean shouldFilter() {
        return true; // execute on every request
    }

    @Override
    public Object run() throws ZuulException {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info("request -> {} request uri is {}", request, request.getRequestURI());
        return null;
    }
}
