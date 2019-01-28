package pers.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"pers.springboot.dao"})
@EnableCaching
@EnableRabbit
@EnableAsync //开启异步
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

