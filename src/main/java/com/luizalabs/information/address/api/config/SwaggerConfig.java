package com.luizalabs.information.address.api.config;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.common.base.Predicates;

import lombok.Generated;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Profile("!test")
@Generated
public class SwaggerConfig {

	@Value("${info.build.version}")
	private String version;
	
    @Value("${spring.application.name}")
    private String appName;

    @Value("${info.build.description}")
    private String description;
	

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("com.luizalabs.information-address-api")
				.securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Collections.singletonList(securityContext()))
				.apiInfo(apiInfo()).select().paths(regex("/.*"))
				.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
				.apis(not(withMethodAnnotation(HideApiDocumentation.class)))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey("Bearer", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/.*")).build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope authorizationScope =
				new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
		return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(appName)
				.description(description)
				.version(version).build();
	}
}
