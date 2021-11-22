package eu.smfinance.todo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer todoId;

	@NotNull
	@Column(nullable = false)
	private Short priority;

	@NotNull
	@Column(nullable = false)
	private String name;

	private String description;

	@NotNull
	@Column(name = "status", columnDefinition = "integer default 1", nullable = false)
	private Short status;
}
