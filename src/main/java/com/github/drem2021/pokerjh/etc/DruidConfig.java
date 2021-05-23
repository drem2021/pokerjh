package com.github.drem2021.pokerjh.etc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    @Bean(destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
//        System.out.println("数据元：");
//		System.out.println("url=" + url);
//		System.out.println("username=" + username);
//		System.out.println("password=" + password);
        return new DruidDataSource();
    }
    @Bean
    public ServletRegistrationBean<Servlet> druidServlet() {
        // 进行 druid 监控的配置处理
        ServletRegistrationBean<Servlet> srb = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 白名单
        srb.addInitParameter("allow", "127.0.0.1");
        // 黑名单
        srb.addInitParameter("deny", "192.168.1.2");
        // 用户名
        srb.addInitParameter("loginUsername", "root");
        // 密码
        srb.addInitParameter("loginPassword", "123456");
        // 是否可以重置数据源
        srb.addInitParameter("resetEnable", "false");
        //srb.setEnabled(true);
        return srb;
    }

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new WebStatFilter());
        // 所有请求进行监控处理
        frb.addUrlPatterns("/*");
        // 排除名单
        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return frb;
    }

}
