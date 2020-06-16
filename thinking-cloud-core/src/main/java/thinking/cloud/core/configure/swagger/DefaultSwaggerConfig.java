package thinking.cloud.core.configure.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import thinking.cloud.core.constant.Constants;
import thinking.cloud.core.exception.UnexpectedExpressionValueException;

@Configuration
@EnableSwagger2
public class DefaultSwaggerConfig {
	@Value("${swagger.docket.type}")
	private String swaggerDocketType ;
	@Value("${swagger.base.package}")
	private String swaggerBasePackage;
	
	@Value("${swagger.api.title}")
	private String swaggerApiTitle;
	@Value("${swagger.api.version}")
	private String swaggerApiVersion;
	@Value("${swagger.api.description}")
	private String swaggerApiDescription;
	
	@Value("${swagger.contact.name}")
	private String swaggerContactName;
	@Value("${swagger.contact.url}")
	private String swaggerContactUrl;
	@Value("${swagger.contact.email}")
	private String swaggerContactEmail;
	
	@Bean
	public Docket createDocket() {
		ApiSelectorBuilder select = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select();
		switch (swaggerDocketType) {
			case Constants.SWAGGER_DOCKET_TYPE_API: 	//设置加了ApiOperation注解的类，才生成接口文档
				select.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
				break;
			case Constants.SWAGGER_DOCKET_TYPE_PACKAGE: //设置包下的类，才生成接口文档
				select.apis(RequestHandlerSelectors.basePackage(swaggerBasePackage));
				break;
			default:
				throw new UnexpectedExpressionValueException(swaggerDocketType);
		}
		System.out.println(swaggerApiTitle);
		return select.paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title(swaggerApiTitle).version(swaggerApiVersion).description(swaggerApiDescription)
					  .contact(new Contact(swaggerContactName, swaggerContactUrl, swaggerContactEmail));
		return apiInfoBuilder.build();
	}
}
