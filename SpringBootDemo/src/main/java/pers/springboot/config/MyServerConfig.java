package pers.springboot.config;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pers.springboot.filter.MyFilter;
import pers.springboot.listener.MyListener;
import pers.springboot.servlet.Myservlet;

import java.util.Arrays;

@Component
public class MyServerConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    //注册三大组件
    //注册Servlet
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new Myservlet(), "/myServlet");
        return servletRegistrationBean;
    }
    //注册Filter
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello", "/myServlet"));
        return registrationBean;
    }
    //注册Listener
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8111);
    }
}
