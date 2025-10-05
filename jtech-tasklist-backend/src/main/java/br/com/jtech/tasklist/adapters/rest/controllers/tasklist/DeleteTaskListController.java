package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.application.ports.input.tasklist.DeleteTaskListInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class DeleteTaskListController
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task-list")
@RequiredArgsConstructor
public class DeleteTaskListController {

  private final DeleteTaskListInputGateway inputGateway;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable("id") String taskId) {
    var userId = request.getAttribute("user_id").toString();
    this.inputGateway.delete(taskId, userId);

    return ResponseEntity.noContent().build();
  }

}
