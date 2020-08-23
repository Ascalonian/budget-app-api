package com.majicode.budgetapp.configuration;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class OpenAPIDocumentationConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("MajiCode Budget Application")
            .description("APIs used to populate and run the 'Majicode Budget App'. \n\nThis is created using the OpenAPI 3.0 specification.\n" +
                    " You can find out more about Swagger at [http://swagger.io](http://swagger.io).\n\n" +
                    "```" +
                    "System.out.println(\"Testing\");\n" +
                    "System.out.println(\"this\");\n" +
                    "System.out.println(\"out\");" +
                    "```")
            .license("MIT")
            .licenseUrl("http://unlicense.org")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .contact(new Contact("Michael Judd","", "judd.michael@gmail.com"))
            .build();
    }

    @Bean
    public Docket fullImplementation(ServletContext servletContext, @Value("${openapi.Budget App Config.base-path:}") String basePath) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Full API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.majicode.budgetapp.api"))
                .paths(PathSelectors.any())
                .build()
                .pathProvider(new BasePathAwareRelativePathProvider(servletContext, basePath))
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(OffsetDateTime.class, Date.class)
                .genericModelSubstitutes(Optional.class)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .tags(new Tag("incomes", "Operations for Income"),
                	  new Tag("groups", "Operations for Groups"));
    }

    @Bean
    public Docket version2Implementation(ServletContext servletContext, @Value("${openapi.Budget App Config.base-path:}") String basePath) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("/v2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.majicode.budgetapp.api"))
                .paths(PathSelectors.ant("/api/v2/**"))
                .build()
                .pathProvider(new BasePathAwareRelativePathProvider(servletContext, basePath))
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(OffsetDateTime.class, Date.class)
                .genericModelSubstitutes(Optional.class)
                .apiInfo(apiInfo())
                .tags(new Tag("incomes", "Operations for Income"),
                        new Tag("groups", "Operations for Groups"));
    }
    
    @Bean
    UiConfiguration uiConfig() {
      return UiConfigurationBuilder.builder()
          .deepLinking(true)
          .displayOperationId(false)
          .defaultModelsExpandDepth(1)
          .defaultModelExpandDepth(1)
          .defaultModelRendering(ModelRendering.EXAMPLE)
          .displayRequestDuration(false)
          .docExpansion(DocExpansion.NONE)
          .maxDisplayedTags(null)
          .operationsSorter(OperationsSorter.ALPHA)
          .showExtensions(false)
          .tagsSorter(TagsSorter.ALPHA)
          .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
          .validatorUrl(null)
          .build();
    }
    
    static class BasePathAwareRelativePathProvider extends RelativePathProvider {
        private final String basePath;

        public BasePathAwareRelativePathProvider(ServletContext servletContext, String basePath) {
            super(servletContext);
            this.basePath = basePath;
        }

        @Override
        protected String applicationPath() {
            return  Paths.removeAdjacentForwardSlashes(UriComponentsBuilder.fromPath(super.applicationPath()).path(basePath).build().toString());
        }

        @Override
        public String getOperationPath(String operationPath) {
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
            return Paths.removeAdjacentForwardSlashes(
                    uriComponentsBuilder.path(operationPath.replaceFirst("^" + basePath, "")).build().toString());
        }
    }

}
