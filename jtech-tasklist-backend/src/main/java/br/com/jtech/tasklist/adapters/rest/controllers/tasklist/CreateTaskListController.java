
/*
 *  @(#)TasklistController.java
 *
 *  Copyright (c) J-Tech Solucoes em Informatica.
 *  All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of J-Tech.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with J-Tech.
 *
 */
package br.com.jtech.tasklist.adapters.rest.controllers.tasklist;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.requests.tasklist.CreateTaskListRequest;
import br.com.jtech.tasklist.application.ports.input.tasklist.CreateTaskListInputGateway;
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
@RequestMapping("/api/v1/task-lists")
@RequiredArgsConstructor
public class CreateTaskListController {

  private final CreateTaskListInputGateway inputGateway;

  @PostMapping
  public ResponseEntity<Void> create(HttpServletRequest request, @Validated @RequestBody CreateTaskListRequest payload) {
    var userId = request.getAttribute("user_id").toString();

    inputGateway.create(payload.to(), userId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}