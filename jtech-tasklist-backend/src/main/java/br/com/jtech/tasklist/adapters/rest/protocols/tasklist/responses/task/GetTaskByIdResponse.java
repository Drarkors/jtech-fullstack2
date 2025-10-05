package br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class GetTaskByIdResponse
 * <p>
 * user rafael.zanetti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTaskByIdResponse {

  private String id;
  private String name;
  private String description;
  private Integer order;
  private String taskListId;

  public static GetTaskByIdResponse of(Task entity) {
    return GetTaskByIdResponse.builder()
      .id(entity.getId())
      .name(entity.getName())
      .description(entity.getDescription())
      .order(entity.getOrder())
      .taskListId(entity.getTaskListId())
      .build();
  }

}
