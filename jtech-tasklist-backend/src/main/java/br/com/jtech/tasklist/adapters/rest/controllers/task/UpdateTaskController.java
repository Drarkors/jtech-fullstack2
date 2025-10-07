package br.com.jtech.tasklist.adapters.rest.controllers.task;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task.GetTaskByIdResponse;
import br.com.jtech.tasklist.application.ports.input.task.UpdateTaskInputGateway;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskInputDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class UpdateTaskController
 * <p>
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class UpdateTaskController {

  private final UpdateTaskInputGateway inputGateway;

  @PutMapping("/{id}")
  public ResponseEntity<GetTaskByIdResponse> update(HttpServletRequest request,
                                                    @PathVariable("id") String taskId,
                                                    @Validated @RequestBody UpdateTaskInputDTO body) {
    var userId = request.getAttribute("user_id").toString();
    var task = this.inputGateway.update(body, taskId, userId);

    return ResponseEntity.ok(GetTaskByIdResponse.of(task));
  }

}
