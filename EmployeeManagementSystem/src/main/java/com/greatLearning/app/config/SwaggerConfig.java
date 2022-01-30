package com.greatLearning.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2

/*
 * working after degrading the spring boot to 2.5.6 Removing @EnableSwagger2
 * annotation link will be http://localhost:port/swagger-ui/index.html
 */
public class SwaggerConfig {


	ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("Employee Management System Web App").description("Employee System").license("MIT")
				.licenseUrl("https://opensource.org/licenses/MIT").version("1.0.0").build();
	}

	@Bean
	public Docket customImplementation() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.greatLearning.app.controller")).build()
				.apiInfo(apiInfo());
	}
}