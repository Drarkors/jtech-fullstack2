package br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * class UpdateTasksOrderResponse
 * <p>
 * user rafael.zanetti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTasksOrderResponse {

  private String id;
  private String name;
  private Set<EmbeddedTask> tasks;

  public static UpdateTasksOrderResponse of(@Validated Set<Task> entities) {
    var parentEntity = entities.stream().findFirst().get().getTaskList();

    return UpdateTasksOrderResponse.builder()
      .id(parentEntity.getId())
      .name(parentEntity.getName())
      .tasks(entities.stream().map(task ->
          new EmbeddedTask(task.getId(), task.getName(), task.getDescription(), task.getOrder()))
        .collect(Collectors.toSet()))
      .build();
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonIgnoreProperties(ignoreUnknown = true)
  static class EmbeddedTask {
    private String id;
    private String name;
    private String description;
    private Integer order;
  }

}
