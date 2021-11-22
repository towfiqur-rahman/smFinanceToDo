package eu.smfinance.todo.services;

import eu.smfinance.todo.dto.ToDoDto;
import eu.smfinance.todo.models.ToDo;

public interface ToDoService {

	ToDo convertDtoToEntity(ToDoDto toDoDto);

	ToDoDto convertEntityToDto(ToDo toDo);
}
