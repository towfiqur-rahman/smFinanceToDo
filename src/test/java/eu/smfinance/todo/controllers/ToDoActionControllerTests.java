package eu.smfinance.todo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eu.smfinance.todo.dto.ToDoDto;
import eu.smfinance.todo.models.ToDo;
import eu.smfinance.todo.repositories.ToDoRepository;
import eu.smfinance.todo.services.ToDoService;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ToDoActionControllerTests {

	private ToDoDto toDoDto;

	@Autowired
	private MockMvc mvc;

	@Mock
	private ToDoRepository todoRepository;

	@InjectMocks
	private ToDoActionController actionController;

	@Autowired
	private ToDoService toDoService;

	private JacksonTester<ToDoDto> jsonTodo;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		toDoDto = getToDoDto();
	}

	@Test
	public void createToDo_success() {
		boolean failed = false;
		try {
			MockHttpServletResponse response = mvc.perform(post("/to-do/create").contentType(MediaType.APPLICATION_JSON)
					.content(jsonTodo.write(toDoDto).getJson())).andReturn().getResponse();
			assertEquals(response.getStatus(), HttpStatus.CREATED.value());
		} catch (IOException e) {
			failed = true;
			e.printStackTrace();
		} catch (Exception e) {
			failed = true;
			e.printStackTrace();
		}
		assertFalse(failed);
	}

	@Test
	@Sql("classpath:todo-data.sql")
	public void updateToDo_success() {
		ToDoDto toDoDto = getToDoDto();
		ToDo toDo = toDoService.convertDtoToEntity(toDoDto);
		toDo.setTodoId(1);

		Short initialStatus = toDo.getStatus();
		toDoDto.setPriority((short) 2);

		boolean failed = false;
		try {
			Integer toDoId = toDo.getTodoId();
			MockHttpServletResponse response = mvc.perform(post("/to-do/update/" + toDoId)
					.contentType(MediaType.APPLICATION_JSON).content(jsonTodo.write(toDoDto).getJson())).andReturn()
					.getResponse();
			assertEquals(response.getStatus(), HttpStatus.ACCEPTED.value());

			Map<String, Object> toDoResponse = getToDoResponse(response.getContentAsString());

			assertNotEquals(initialStatus.intValue(), toDoResponse.get("priority"));
		} catch (IOException e) {
			failed = true;
			e.printStackTrace();
		} catch (Exception e) {
			failed = true;
			e.printStackTrace();
		}
		assertFalse(failed);
	}

	@Test
	@Sql("classpath:todo-data.sql")
	public void markToDoComplete_success() {
		ToDo toDo = toDoService.convertDtoToEntity(toDoDto);
		Integer toDoId = new Integer(1);
		toDo.setTodoId(toDoId);
		boolean success = false;
		try {

			MockHttpServletResponse response = mvc
					.perform(post("/to-do/mark-complete/" + toDoId).contentType(MediaType.APPLICATION_JSON)).andReturn()
					.getResponse();
			assertEquals(response.getStatus(), HttpStatus.ACCEPTED.value());

			Map<String, Object> toDoResponse = getToDoResponse(response.getContentAsString());
			System.out.println("status is " + toDoResponse.get("status"));
			assertEquals(Integer.parseInt(String.valueOf(toDoResponse.get("status"))), 6);
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(success);
	}

	private ToDoDto getToDoDto() {
		ToDoDto toDoDto = new ToDoDto();
		toDoDto.setName("Task 1");
		toDoDto.setDescription("Task 1 description");
		toDoDto.setPriority((short) 1);
		toDoDto.setStatus((short) 1);

		return toDoDto;

	}

	private Map<String, Object> getToDoResponse(String responseContent)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		@SuppressWarnings("unchecked")
		Map<String, Object> toDoResponse = objectMapper.readValue(responseContent, Map.class);
		return toDoResponse;
	}
}
