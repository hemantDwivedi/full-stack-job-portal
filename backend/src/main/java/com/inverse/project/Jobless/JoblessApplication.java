package com.inverse.project.Jobless;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Jobless - Job Portal RESTful APIs",
				description = "Spring Boot RESTful APIs for Job portal",
				version = "1.0.0",
				contact = @Contact(
						name = "Hemant Kumar",
						email = "hemantdwiwedi@gmail.com",
						url = "https://github.com/hemantDwivedi"
				),
				license = @License(
						name = "Apache 2.4",
						url = "https://httpd.apache.org/"
				)
		)
)
public class JoblessApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoblessApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
