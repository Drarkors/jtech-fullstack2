package br.com.jtech.tasklist.adapters.rest.controllers.task;

import br.com.jtech.tasklist.application.ports.input.task.DeleteTaskInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class DeleteTaskController
 * <p>
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class DeleteTaskController {

  private final DeleteTaskInputGateway inputGateway;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(HttpServletRequest request,
                                     @PathVariable String id) {
    var userId = request.getAttribute("user_id").toString();
    this.inputGateway.delete(id, userId);

    return ResponseEntity.noContent().build();
  }
}
