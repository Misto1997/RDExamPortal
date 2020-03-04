package com.epam.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.epam.controller")).paths(PathSelectors.any()).build()
				.apiInfo(metaData());

	}

	private ApiInfo metaData() {
		return new ApiInfo("View service documentation", "auto generated documentation for view controllers", "1.0",
				"Terms of service", new Contact("Rahul Rana", null, "rahulrana1997.rr@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

	}

}
