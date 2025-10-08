package br.com.jtech.tasklist.adapters.rest.controllers.task;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task.GetTaskByIdResponse;
import br.com.jtech.tasklist.application.ports.input.task.ToggleTaskIsDoneInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ToggleTaskIsDoneController {

  private final ToggleTaskIsDoneInputGateway inputGateway;

  @PatchMapping("/{id}/is-done")
  public ResponseEntity<GetTaskByIdResponse> update(HttpServletRequest request,
                                                    @PathVariable("id") String taskId) {
    var userId = request.getAttribute("user_id").toString();
    var task = this.inputGateway.toggleIsDone(taskId, userId);

    return ResponseEntity.ok(GetTaskByIdResponse.of(task));
  }

}
