package eu.smfinance.todo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import eu.smfinance.todo.models.ToDo;
import eu.smfinance.todo.repositories.ToDoRepository;

@ExtendWith(MockitoExtension.class)
class ToDoViewControllerTest {

	final ToDo toDo1 = new ToDo(1, (short) 2, "Lokesh", "Gupta", (short) 3);
	final ToDo toDo2 = new ToDo(2, (short) 4, "Alex", "Gussin", (short) 2);

	@InjectMocks
	private ToDoViewController toDoController;

	@Mock
	private ToDoRepository toDoRepository;

	@Test
	public void testFindAll() {

		final List<ToDo> allToDos = Arrays.asList(toDo1, toDo2);
		Page<ToDo> pageAll = getTestPage(allToDos);
		when(toDoRepository.findAllByOrderByPriorityDesc(PageRequest.of(0, 20))).thenReturn(pageAll);

		ResponseEntity<Page<ToDo>> result = toDoController.showAllToDos(0, 20);

		assertEquals(result.getBody().getContent().size(), 2);

		assertEquals(result.getBody().getContent().get(0).getName(), toDo1.getName());

	}

	@Test
	public void testFindAllOrderByPriority() {
		final List<ToDo> allToDos = Arrays.asList(toDo2, toDo1);
		Page<ToDo> pageAll = getTestPage(allToDos);
		when(toDoRepository.findAllByOrderByPriorityDesc(PageRequest.of(0, 20))).thenReturn(pageAll);

		ResponseEntity<Page<ToDo>> result = toDoController.showAllToDos(0, 20);

		assertEquals(result.getBody().getContent().size(), 2);

		assertEquals(result.getBody().getContent().get(0).getName(), toDo2.getName());
	}

	private Page<ToDo> getTestPage(final List<ToDo> toDos) {
		return new Page<ToDo>() {
			public List<ToDo> getContent() {
				return toDos;
			}

			public int getNumber() {
				return 1;
			}

			public int getNumberOfElements() {
				return toDos.size();
			}

			public int getSize() {
				return toDos.size();
			}

			public Sort getSort() {
				return null;
			}

			public boolean hasContent() {
				return true;
			}

			public boolean hasNext() {
				return false;
			}

			public boolean hasPrevious() {
				return false;
			}

			public boolean isFirst() {
				return true;
			}

			public boolean isLast() {
				return true;
			}

			public Pageable nextPageable() {
				return null;
			}

			public Pageable previousPageable() {
				return null;
			}

			public Iterator<ToDo> iterator() {
				return null;
			}

			public long getTotalElements() {
				return toDos.size();
			}

			public int getTotalPages() {
				return 1;
			}

			public <U> Page<U> map(Function<? super ToDo, ? extends U> converter) {
				return null;
			}
		};
	}
}
