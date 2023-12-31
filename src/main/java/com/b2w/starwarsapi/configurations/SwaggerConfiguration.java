package com.b2w.starwarsapi.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
        		.apis(RequestHandlerSelectors.basePackage("com.b2w.starwarsapi")) // melhorar aqui, tirar essa string do pacote!
	            .paths(PathSelectors.any())
	            .build()
	            .apiInfo(info());
    }

    private ApiInfo info() {
        return new ApiInfoBuilder().title("Star Wars API")
                                   .description("Proyecto API de Star Wars usando Java, Spring boot y MongoDB")
                                   .contact(new Contact("Jose Albeiro Marin", "https://github.com/jamarin1994/apiStarWars.git",
                                                        "josealbeiromarin1994@gmail.com"))
                                   .version("1.0.0")
                                   .license("Apache License Version 2.0")
                                   .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                                   .build();
    }
	
	
	
}
