package eu.smfinance.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.smfinance.todo.models.ToDo;
import eu.smfinance.todo.repositories.ToDoRepository;

@RestController
@RequestMapping("/to-dos")
public class ToDoViewController {

	@Autowired
	private ToDoRepository todoRepository;

	@GetMapping
	public ResponseEntity<Page<ToDo>> showAllToDos(
			@RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(name = "size", defaultValue = "20", required = false) Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return ResponseEntity.ok(todoRepository.findAllByOrderByPriorityDesc(pageable));
	}
}
