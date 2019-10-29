package top.zerotop.config;

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

@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig  {
    @Bean
    public Docket AdminApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(productApiInfo())
                .groupName("admin")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.zerotop.blog.web"))
                .paths(PathSelectors.regex("/api/admin/v1.*"))
                .build();
    }

    @Bean
    public Docket ProductApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(productApiInfo())
                .groupName("api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.zerotop.blog.web"))
                .paths(PathSelectors.regex("/api/v1.*"))
                .build();
    }

    @Bean
    public Docket TestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(productApiInfo())
                .groupName("test")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.zerotop.blog.web"))
                .paths(PathSelectors.regex("/test/api.*"))
                .build();
    }

    private ApiInfo productApiInfo() {
        return new ApiInfoBuilder()
                .title("blog接口")
                .description("blog请求接口api")
                .termsOfServiceUrl("http://localhost:8088/blog" )
                .contact(new Contact("zerotop", "https://0top.github.io", ""))
                .version("2.0")
                .build();
    }
}
