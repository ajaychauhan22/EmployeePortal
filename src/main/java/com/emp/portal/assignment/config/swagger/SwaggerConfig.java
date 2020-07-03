package com.emp.portal.assignment.config.swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author ajaychauhan01
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	// @Bean
	public Docket api0() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public Docket api() {

		SecurityReference securityReference = SecurityReference.builder().reference("basicAuth")
				.scopes(new AuthorizationScope[0]).build();

		ArrayList<SecurityReference> reference = new ArrayList<>(1);
		reference.add(securityReference);

		ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
		securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

		ArrayList<SecurityScheme> auth = new ArrayList<>(1);
		auth.add(new BasicAuth("basicAuth"));

		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(auth).securityContexts(securityContexts).select()
				.apis(RequestHandlerSelectors.basePackage("com.emp.portal.assignment")).paths(PathSelectors.any())
				.build();
	}
}
