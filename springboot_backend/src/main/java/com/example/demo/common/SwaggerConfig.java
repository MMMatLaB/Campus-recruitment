package com.example.demo.common;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket productApi() {
        Docket build = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  //添加ApiOperiation注解的被扫描
                .paths(PathSelectors.any())
                .build();
        return build;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("虚拟仿真系统的API文档").description("")
                .version("1.0").build();
    }
    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .consumes(Collections.singleton("multipart/form-data"))
                .produces(Collections.singleton("application/json"))
                .globalOperationParameters(Arrays.asList(
                        new ParameterBuilder()
                                .name("Content-Type")
                                .description("Content Type Header")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .defaultValue("application/json")
                                .build(),
                        new ParameterBuilder()
                                .name("Accept")
                                .description("Accept Header")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .defaultValue("application/json")
                                .build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Your API Title")
                .description("Your API Description")
                .version("1.0")
                .build();
    }*/
}
