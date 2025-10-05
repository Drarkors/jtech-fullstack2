package br.com.jtech.tasklist.adapters.rest.controllers;

import br.com.jtech.tasklist.application.core.entities.Task;
import br.com.jtech.tasklist.application.ports.input.task.GetTaskByIdInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class GetTaskByIdController
 * <p>
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class GetTaskByIdController {

  private final GetTaskByIdInputGateway inputGateway;

  @GetMapping("/{id}")
  public ResponseEntity<Task> getById(HttpServletRequest request, @PathVariable("id") String taskId) {
    var userId = request.getAttribute("user_id").toString();
    var task = this.inputGateway.getById(taskId, userId);

    return ResponseEntity.ok(task);
  }

}
