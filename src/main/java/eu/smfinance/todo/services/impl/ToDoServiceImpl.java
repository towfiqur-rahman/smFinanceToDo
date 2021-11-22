package eu.smfinance.todo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.smfinance.todo.dto.ToDoDto;
import eu.smfinance.todo.models.ToDo;
import eu.smfinance.todo.services.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ModelMapper modelMapper;

	public ToDo convertDtoToEntity(ToDoDto toDoDto) {
		ToDo toDo = modelMapper.map(toDoDto, ToDo.class);
		return toDo;
	}

	public ToDoDto convertEntityToDto(ToDo toDo) {
		ToDoDto toDoDto = modelMapper.map(toDo, ToDoDto.class);
		return toDoDto;
	}
}
