package cn.hjgx.component;


import cn.hjgx.entity.UserAdministrator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alvin on 2017/8/7.
 */

@WebFilter(filterName="AuthorityFilter",urlPatterns="/backstage/*")
public class AuthorityFilter implements Filter {

    /**
     * 保存/取得管理员登录用户键
     */
    public static final String LOGIN_ADMIN = "login_admin";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        UserAdministrator user = (UserAdministrator) session.getAttribute(LOGIN_ADMIN);
        if(user == null){
            //TODO 用户未登录，跳转至登陆页面
            response.sendRedirect("/manage/login");
        }else{
            filterChain.doFilter(request,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
