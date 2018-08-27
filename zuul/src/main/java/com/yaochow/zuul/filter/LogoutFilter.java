package com.yaochow.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * 注销过滤器
 */

@Component
public class LogoutFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        if (Objects.equals("/user/logout", uri)) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() {
        log.info("log out filter begin");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        String uid = (String) request.getSession().getAttribute("uid");
        if (StringUtils.isNotBlank(uid)) {
            try {
                //将session内的uid置空
                request.getSession().setAttribute("uid", null);
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                JSONObject result = new JSONObject();
                result.put("success", true);
                ctx.getResponse().getWriter().write(result.toJSONString());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        log.info("log out filter finish");
        return null;
    }
}
