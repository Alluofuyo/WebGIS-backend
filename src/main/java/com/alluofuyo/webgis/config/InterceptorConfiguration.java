package com.alluofuyo.webgis.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.alluofuyo.webgis.interceptor.CheckLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfiguration {
    @Bean
    public WebMvcConfigurer tokenInterceptorConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
//                    SaRouter.match(List.of("/**"), Arrays.asList("/user/login", "/user/regist"),
//                            StpUtil::checkLogin);
//
//                })).addPathPatterns("/**");

                registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(
                        "/user/login", "/user/regist");
            }
        };
    }
}
