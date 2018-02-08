package cn.hjgx.configuration;

import cn.hjgx.component.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * @author Alvin Du
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 添加静态资源过滤
     * @param registry
     */
    @Override  
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/");
        //场地图片静态资源配置
        registry.addResourceHandler(env.getProperty("image.preview.reqRoute", String.class) + "/**").addResourceLocations("file:" + env.getProperty("image.preview.path", String.class) + File.separator);

        //TODO 广告图片静态资源配置

        registry.addResourceHandler("/templates/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/templates/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
        super.addResourceHandlers(registry);

    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加登录拦截器
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);

    }

}