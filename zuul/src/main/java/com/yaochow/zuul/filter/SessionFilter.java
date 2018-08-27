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
 * 登陆过滤器：检查用户是否已登陆
 */
@Component
public class SessionFilter extends ZuulFilter {

    private Logger log = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 登陆、注销、注册不需要过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();
        if (Objects.equals("/user/login", uri) ||
                Objects.equals("/user/logout", uri) ||
                Objects.equals("/user/register", uri) ||
                Objects.equals("/user/confirm", uri)) {
            return false;
        }
        return true;
    }

    /**
     * 过滤检查session内uid是否有值
     *
     * @return
     */
    @Override
    public Object run() {
        log.info("user filter begin");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uid = (String) request.getSession().getAttribute("uid");
        if (StringUtils.isBlank(uid)) {
            try {
                JSONObject result = new JSONObject();
                result.put("success", false);
                result.put("errorMsg", "Sign In First");
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                ctx.getResponse().getWriter().write(result.toJSONString());
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        log.info("user filter finish");
        return null;
    }
}
