package br.com.adaconsultoria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket employeeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.adaconsultoria.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    //create api metadata that goes at the top of the generated page
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Studio API")
                .version("1.0")
                .description("API for managing studio.")
                .contact(new Contact("ADA Consultoria", "http://adaconsultoria.com.br", "contato@adaconsultoria.com.br"))
                .license("Apache License Version 2.0")
                .build();
    }


	
	
}