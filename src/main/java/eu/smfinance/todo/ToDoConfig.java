package eu.smfinance.todo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToDoConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
