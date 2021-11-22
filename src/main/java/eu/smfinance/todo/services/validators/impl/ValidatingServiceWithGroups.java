package eu.smfinance.todo.services.validators.impl;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import eu.smfinance.todo.dto.ToDoDto;
import eu.smfinance.todo.services.validators.OnCreate;
import eu.smfinance.todo.services.validators.OnUpdate;

@Service
@Validated
public class ValidatingServiceWithGroups {

    @Validated(OnCreate.class)
    void validateForCreate(@Valid ToDoDto toDo){
      // do something
    }

    @Validated(OnUpdate.class)
    void validateForUpdate(@Valid ToDoDto toDo){
      // do something
    }

}
