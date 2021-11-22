package eu.smfinance.todo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {

	private String fieldName;

	private String message;
}
