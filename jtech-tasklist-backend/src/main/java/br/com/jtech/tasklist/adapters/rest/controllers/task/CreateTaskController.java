package br.com.jtech.tasklist.adapters.rest.controllers.task;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.requests.task.CreateTaskRequest;
import br.com.jtech.tasklist.application.ports.input.task.CreateTaskInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class CreateTaskListController
 * <p>
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class CreateTaskController {

  private final CreateTaskInputGateway inputGateway;

  @PostMapping
  public ResponseEntity<Void> create(HttpServletRequest request, @Validated @RequestBody CreateTaskRequest payload) {
    var userId = request.getAttribute("user_id").toString();
    inputGateway.create(payload.to(), userId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
