package com.wangcl.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.tuckey.web.filters.urlrewrite.Conf;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
public class UrlRewriteFilterConfig extends UrlRewriteFilter {

    private static final String URL_REWRITE = "classpath:/urlrewrite.xml";

    // Inject the Resource from the given location
    @Value(URL_REWRITE)
    private Resource resource;

    @Bean
    public FilterRegistrationBean urlRewrite(){
        UrlRewriteFilter rewriteFilter=new UrlRewriteFilter();
        FilterRegistrationBean registration = new FilterRegistrationBean(rewriteFilter);
        registration.setUrlPatterns(Arrays.asList("/*"));
        Map initParam=new HashMap();
        initParam.put("confPath","urlrewirte.xml");
        initParam.put("infoLevel","INFO");
        registration.setInitParameters(initParam);
        return registration;
    }

}