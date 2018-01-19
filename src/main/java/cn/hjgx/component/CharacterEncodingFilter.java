package cn.hjgx.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName="CharacterEncodingFilter",urlPatterns="/*")
public class CharacterEncodingFilter implements Filter {

    @Autowired
    private Environment env;

    private String characterEncoding;
    private boolean enabled;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (enabled || characterEncoding != null) {
            servletRequest.setCharacterEncoding(characterEncoding);
            servletResponse.setCharacterEncoding(characterEncoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        enabled = env.getProperty("huidashi.characterEncoding.enable",Boolean.class);
        characterEncoding = env.getProperty("huidashi.characterEncoding",String.class);
    }
}
