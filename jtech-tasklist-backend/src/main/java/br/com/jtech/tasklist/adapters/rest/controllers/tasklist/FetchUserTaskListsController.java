package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist.FetchUserTaskListsResponse;
import br.com.jtech.tasklist.application.ports.input.tasklist.FetchUserTaskListsInputGateway;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * class FetchUserTaskListsController
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task-list")
@RequiredArgsConstructor
public class FetchUserTaskListsController {

  private final FetchUserTaskListsInputGateway inputGateway;

  @GetMapping
  public ResponseEntity<Set<FetchUserTaskListsResponse>> fetchUserTaskLists(HttpServletRequest request) {
    var userId = request.getAttribute("user_id").toString();
    var taskLists = this.inputGateway.fetchTaskLists(userId);

    return ResponseEntity.ok(taskLists.stream().map(FetchUserTaskListsResponse::of).collect(Collectors.toSet()));
  }


}
