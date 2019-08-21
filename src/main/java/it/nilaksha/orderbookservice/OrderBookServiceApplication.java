package it.nilaksha.orderbookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
public class OrderBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderBookServiceApplication.class, args);
	}

	@Bean
	public Docket swagger(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("it.nilaksha.orderbookservice.controller"))
				.paths(regex("api/v1/.*"))
				.build()
				.apiInfo(buildApiInfo());
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title("Order Book Service API")
				.description("API documentation for Order Book Service ")
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.1.0-SNAPSHOT")
				.build();
	}

}
