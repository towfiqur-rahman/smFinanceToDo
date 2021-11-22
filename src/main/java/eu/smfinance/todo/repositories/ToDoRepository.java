package eu.smfinance.todo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import eu.smfinance.todo.models.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {

	Page<ToDo> findAllByOrderByPriorityDesc(Pageable pageable);
}
