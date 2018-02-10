package cn.hjgx.component;

import cn.hjgx.Annotation.Login;
import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.entity.UserBusiness;
import cn.hjgx.entity.paramDto.ResultDto;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 提供一些零碎的，但是需要权限认证的操作，更多是为异步登录准备
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    //前台用户登录校验键
    public static final String LOGIN_USER = "login_user";



    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Login loginCheck = handlerMethod.getMethodAnnotation(Login.class);
        ResultDto resultDto = new ResultDto();

        // /如果方法上没有Login注解，则校验通过
        if (loginCheck != null) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            HttpSession session = request.getSession();
            UserBusiness user = (UserBusiness) session.getAttribute(LOGIN_USER);
            if (user == null) {
                //用户未登录，返回未登录标识
                resultDto.setFlag(-1);
                resultDto.setMessage("你需要登录才能进行该操作");

                servletResponse.setCharacterEncoding("utf-8");
                servletResponse.setContentType("application/json");
                servletResponse.getWriter().print(JsonUtil.toJson(resultDto));
                return false;
            }
        }
        return true;
    }

}