package com.sergio.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "AccessFilter", urlPatterns = {"/product"})
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
       String checkBox = req.getParameter("accessToEnter");
       if(checkBox!=null){
           req.getRequestDispatcher("/products").forward(req,resp);
       } else {
           req.getRequestDispatcher("/error").forward(req,resp);
       }
//       filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
