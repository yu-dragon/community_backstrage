package com.yulong.manager.filter;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {

    private String loginNoFilter = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.loginNoFilter = filterConfig.getInitParameter("noFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if (loginNoFilter.equals(requestURI)
                || "/noLogin".equals(requestURI)
                || requestURI.startsWith("/images")
                || requestURI.startsWith("/reg")) {
            System.out.println("执行" + requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();
        Object cardId = session.getAttribute("card_id");
        if (cardId == null || "".equals(cardId)) {
            System.out.println("执行了cardId为null，没有登录");
            response.sendRedirect("/noLogin");
            return;
        }
        System.out.println("已登录：" + cardId + " 过期时间是：" + session.getMaxInactiveInterval());
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
