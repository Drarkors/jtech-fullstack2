package br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * class GetTaskListByIdResponse
 * <p>
 * user rafael.zanetti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaskListResponse implements Serializable {

  private String id;
  private String name;
  private String description;
  private Integer order;

  public static UpdateTaskListResponse of(@Validated TaskList entity) {
    return UpdateTaskListResponse.builder()
      .id(entity.getId())
      .name(entity.getName())
      .description(entity.getDescription())
      .order(entity.getOrder())
      .build();
  }
  
}
