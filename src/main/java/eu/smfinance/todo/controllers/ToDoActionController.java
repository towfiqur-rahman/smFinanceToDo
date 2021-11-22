package eu.smfinance.todo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.smfinance.todo.dto.ToDoDto;
import eu.smfinance.todo.models.ToDo;
import eu.smfinance.todo.repositories.ToDoRepository;
import eu.smfinance.todo.services.ToDoService;

@RestController
@RequestMapping("/to-do/")
public class ToDoActionController {

	@Autowired
	private ToDoRepository todoRepository;

	@Autowired
	private ToDoService toDoService;

	@PostMapping("create")
	public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoDto toDoDto) {
		ToDo toDo = todoRepository.save(toDoService.convertDtoToEntity(toDoDto));
		return new ResponseEntity<ToDo>(toDo, HttpStatus.CREATED);
	}

	@PostMapping("update/{toDoId}")
	public ResponseEntity<?> updateToDo(@PathVariable Integer toDoId, @RequestBody ToDoDto toDoDto) {
		ToDo existingToDo = todoRepository.findById(toDoId).orElse(null);
		if (existingToDo == null) {
			return new ResponseEntity<String>("ToDo with id does not exit", HttpStatus.NOT_FOUND);
		}
		toDoDto.setTodoId(toDoId);
		ToDo toDo = todoRepository.save(toDoService.convertDtoToEntity(toDoDto));
		return new ResponseEntity<ToDo>(toDo, HttpStatus.ACCEPTED);
	}

	@PostMapping("mark-complete/{toDoId}")
	public ResponseEntity<?> markToDoComplete(@PathVariable Integer toDoId) {
		ToDo toDo = todoRepository.findById(toDoId).orElse(null);
		if (toDo == null) {
			return new ResponseEntity<String>("ToDo with id does not exit", HttpStatus.NOT_FOUND);
		}
		toDo.setStatus((short) 6);
		todoRepository.save(toDo);
		return new ResponseEntity<ToDo>(toDo, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("delete/{toDoId}")
	public ResponseEntity<String> deleteToDo(@PathVariable Integer toDoId) {
		todoRepository.deleteById(toDoId);
		return new ResponseEntity<String>("deleted" , HttpStatus.NO_CONTENT);
	}
}
