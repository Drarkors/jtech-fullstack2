package br.com.jtech.tasklist.adapters.rest.controllers;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist.GetTaskListByIdResponse;
import br.com.jtech.tasklist.application.ports.input.tasklist.GetTaskListByIdInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class GetTaskListByIdController
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task-list")
@RequiredArgsConstructor
public class GetTaskListByIdController {

  private final GetTaskListByIdInputGateway inputGateway;

  @GetMapping("/{id}")
  public ResponseEntity<GetTaskListByIdResponse> getById(HttpServletRequest request, @PathVariable("id") String taskId) {
    var userId = request.getAttribute("user_id").toString();
    var taskList = this.inputGateway.getById(taskId, userId);

    return ResponseEntity.ok(GetTaskListByIdResponse.of(taskList));
  }

}
