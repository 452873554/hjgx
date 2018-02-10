package cn.hjgx.component;


import cn.hjgx.entity.UserAdministrator;
import cn.hjgx.entity.UserBusiness;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 商家后台权限校验
 */
@WebFilter(filterName="ForeGroundAuthorityFilter",urlPatterns="/usercenter/*")
public class ForeGroundAuthorityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        UserBusiness user = (UserBusiness) session.getAttribute(LoginInterceptor.LOGIN_USER);
        if(user == null){
            //TODO 用户未登录，跳转至登陆页面
            response.sendRedirect("/user/login.html");
        }else{
            filterChain.doFilter(request,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
