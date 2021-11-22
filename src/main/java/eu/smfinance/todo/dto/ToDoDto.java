package eu.smfinance.todo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import eu.smfinance.todo.services.validators.OnCreate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoDto {

	private Integer todoId;

	@NotNull(groups = OnCreate.class, message = "Priority cannot be null")
	@Min(value = 1, message = "Priority must be at least 1")
	@Max(value = 6, message = "Priority must be at most 6")
	private Short priority;

	@NotNull(groups = OnCreate.class, message = "Name cannot be null")
	@Length(min = 1, max = 63, message = "Name must be between 1 and 63 characters")
	private String name;

	@Length(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
	private String description;

	@NotNull(groups = OnCreate.class, message = "Status cannot be null")
	@Min(value = 1, message = "Status must be at least 1", groups = OnCreate.class)
	@Max(value = 6, message = "Status must be at most 6")
	private Short status;
}
