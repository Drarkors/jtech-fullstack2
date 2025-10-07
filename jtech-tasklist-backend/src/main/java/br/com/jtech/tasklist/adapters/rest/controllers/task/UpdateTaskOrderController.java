package br.com.jtech.tasklist.adapters.rest.controllers.task;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task.UpdateTasksOrderResponse;
import br.com.jtech.tasklist.application.ports.input.task.UpdateTasksOrderInputGateway;
import br.com.jtech.tasklist.application.ports.input.task.dtos.UpdateTaskOrderInputDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * class UpdateTaskOrderController
 * <p>
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task-lists/{taskListId}/tasks")
@RequiredArgsConstructor
public class UpdateTaskOrderController {

  private final UpdateTasksOrderInputGateway inputGateway;

  @PatchMapping("/order")
  public ResponseEntity<UpdateTasksOrderResponse> updateOrders(HttpServletRequest request,
                                                               @PathVariable String taskListId,
                                                               @Validated @RequestBody Set<UpdateTaskOrderInputDTO> body) {
    var userId = request.getAttribute("user_id").toString();
    var tasks = this.inputGateway.updateOrder(body, taskListId, userId);

    return ResponseEntity.ok(UpdateTasksOrderResponse.of(tasks));
  }

}
