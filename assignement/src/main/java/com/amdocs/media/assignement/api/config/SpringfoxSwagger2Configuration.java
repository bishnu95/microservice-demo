package com.amdocs.media.assignement.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxSwagger2Configuration {
	private static final Logger LOG = LoggerFactory.getLogger( SpringfoxSwagger2Configuration.class );

	@Bean
	public Docket api( 
			@Value("${api.title:}") String title,
			@Value("${api.description:}") String description,
			@Value("${api.version:}") String version,
			@Value("${api.termsOfService:}") String termsOfService,
			@Value("${api.license.name:}") String licenseName,
			@Value("${api.license.url:}") String licenseUrl,
			@Value("${api.contact.name:}") String contactName,
			@Value("${api.contact.email:}") String contactEmail,
			@Value("${api.contact.url:}") String contactUrl,
			@Value("${api.restPackage:}") String restPackage,
			TypeResolver typeResolver) {
		
		LOG.debug( "Loading  springfox-swagger2 Docket configuration..." );
		return new Docket( DocumentationType.SWAGGER_2 )
				.select()
				.apis( StringUtils.hasText( restPackage ) ? 
						RequestHandlerSelectors.basePackage(restPackage) :
						RequestHandlerSelectors.withClassAnnotation( RestController.class ) )
				.paths( PathSelectors.any() )
				.build()
				.apiInfo(new ApiInfoBuilder()
						.title( title )
						.description( description )
						.version( version )
						.termsOfServiceUrl( termsOfService )
						.contact( new Contact( contactName, contactUrl, contactEmail) )
						.license( licenseName)
						.licenseUrl( licenseUrl )
						.build());
	}
	 
}
