package pers.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    //初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized...web应用启动");
    }
    //销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed...web应用项目销毁");
    }
}
