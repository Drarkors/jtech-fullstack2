package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist.GetTaskListByIdResponse;
import br.com.jtech.tasklist.application.ports.input.tasklist.UpdateTaskListInputGateway;
import br.com.jtech.tasklist.application.ports.input.tasklist.dtos.UpdateTaskListInputDTO;
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
 * class UpdateTaskListController
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task-list")
@RequiredArgsConstructor
public class UpdateTaskListController {

  private final UpdateTaskListInputGateway inputGateway;

  @PutMapping("/{id}")
  public ResponseEntity<GetTaskListByIdResponse> update(HttpServletRequest request,
                                                        @PathVariable("id") String taskId,
                                                        @Validated @RequestBody UpdateTaskListInputDTO body) {
    var userId = request.getAttribute("user_id").toString();
    var taskList = this.inputGateway.update(body, taskId, userId);

    return ResponseEntity.ok(GetTaskListByIdResponse.of(taskList));
  }

}
