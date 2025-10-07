package br.com.jtech.tasklist.application.core.entities;

import br.com.jtech.tasklist.adapters.database.repositories.models.TaskModel;
import br.com.jtech.tasklist.config.infra.exceptions.constraints.UUIDConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(exclude = {"order", "description", "isDone"})
@NoArgsConstructor
@AllArgsConstructor
public class Task {

  @UUIDConstraint
  private String id;

  @UUIDConstraint
  private String taskListId;

  @NotNull
  @NotEmpty
  @Size(min = 4, max = 100, message = "Field \"name\" must have from 4 up to 100 characters")
  private String name;

  @Size(max = 500, message = "Field \"description\" can have up to 500 characters")
  private String description;

  @NotNull
  @Min(value = 0, message = "Field \"order\" can not be lower than 0")
  @Max(value = Integer.MAX_VALUE, message = "Field \"order\" can go up to " + Integer.MAX_VALUE)
  private Integer order;

  @NotNull
  @Builder.Default
  private Boolean isDone = Boolean.FALSE;

  private TaskList taskList;

  public static List<Task> of(List<TaskModel> models) {
    return models.stream()
      .map(Task::of)
      .toList();
  }

  public static Task of(TaskModel model) {
    return Task.builder()
      .id(model.getId().toString())
      .taskListId(model.getTaskListId().toString())
      .name(model.getName())
      .description(model.getDescription())
      .order(model.getOrder())
      .isDone(model.getIsDone())
      .taskList(model.getTaskList() != null ? TaskList.of(model.getTaskList()) : null)
      .build();
  }

  public TaskModel toModel() {
    return TaskModel.builder()
      .id(getId() != null ? UUID.fromString(getId()) : null)
      .taskListId(UUID.fromString(getTaskListId()))
      .name(getName())
      .description(getDescription())
      .order(getOrder())
      .isDone(getIsDone())
      .taskList(getTaskList() != null ? getTaskList().toModel() : null)
      .build();
  }

}
