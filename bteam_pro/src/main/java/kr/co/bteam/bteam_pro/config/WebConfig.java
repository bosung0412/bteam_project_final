package kr.co.bteam.bteam_pro.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAutoConfiguration
public class WebConfig implements WebMvcConfigurer {

   @Bean
   public WebMvcConfigurer crosConfigure() {
      return new WebMvcConfigurer() {
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            System.out.println("Test==========");
            registry.addMapping("/**")
                  .allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost", "http://localhost/api/v1/auth/**")
                  .allowedHeaders("*")
                  .allowedMethods("GET", "POST", "PUT", "DELETE")
                  .maxAge(3600);

            // addMapping - CORS를 적용할 url의 패턴을 정의 (/** 로 모든 패턴을 가능하게 함)
            // allowedOrigins - 허용할 origin을 정의 (* 로 모든 origin을 허용, 여러개도 지정가능)
            // allowedMethods - HTTP Method를 지정 (* 로 모든 Method를 허용)
            // maxAge - 원하는 시간만큼 request를 cashing함
         }

      };
   }
}