package com.fixedasset.common.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        //Allow origins for cross-domain requests
        config.addAllowedOrigin("/*");

        //Cookie information is allowed to be carried across domains. By default, cross-domain requests do not carry cookie information.
        config.setAllowCredentials(true);

        //Allow requests with methods
        config.addAllowedMethod("/*");
        //config.setAllowedMethods(Arrays.asList("GET", "PUT", "POST","DELETE"));
        //config.addAllowedMethod(HttpMethod.POST);

        //Allow requests Header
        config.addAllowedHeader("/*");
        //config.addAllowedHeader("x-firebase-auth");

        //Which Header can use（因為跨網域預設不能取得全部Header資訊）
        config.addExposedHeader("/*");
        //config.addExposedHeader("Content-Type");
        //config.addExposedHeader( "X-Requested-With");
        //config.addExposedHeader("accept");
        //config.addExposedHeader("Origin");
        //config.addExposedHeader( "Access-Control-Request-Method");
        //config.addExposedHeader("Access-Control-Request-Headers");


        //映射路徑
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //return一個的CorsFilter.
        return new CorsFilter(configSource);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //映射路徑
                registry.addMapping("/**")
                        //允許跨網域請求的來源
                        .allowedOrigins("/*")
                        //允許跨域攜帶cookie資訊，預設跨網域請求是不攜帶cookie資訊的。
                        .allowCredentials(true)
                        //允許使用那些請求方式
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        //允許哪些Header
                        .allowedHeaders("/*")
                        //可獲取哪些Header（因為跨網域預設不能取得全部Header資訊）
                        .exposedHeaders("Header1", "Header2");
            }
        };
    }
}
