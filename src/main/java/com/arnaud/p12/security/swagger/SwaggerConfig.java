//package com.arnaud.asso.p12.security.swagger;
//
//import com.google.common.base.Predicates;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.RequestHandler;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket docket(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(
//                        new ApiInfoBuilder()
//                                .description("a définir")
//                                .title("Association API").version("1.0")
//                                .build()
//                ).select()
//                .apis(RequestHandlerSelectors.basePackage("com.arnaud.asso.p12"))
//                .build();
//
//    }
//
//
//}
