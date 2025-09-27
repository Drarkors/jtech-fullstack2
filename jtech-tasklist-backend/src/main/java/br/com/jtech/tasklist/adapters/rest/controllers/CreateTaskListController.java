
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
package br.com.jtech.tasklist.adapters.rest.controllers;

import br.com.jtech.tasklist.adapters.rest.protocols.TaskListRequest;
import br.com.jtech.tasklist.application.ports.input.CreateTaskListInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.jtech.tasklist.application.core.entities.TaskList.of;

/**
 * class TaskListController
 * <p>
 * user rafael.zanetti
 */
@RestController
@RequestMapping("/api/v1/task-lists")
@RequiredArgsConstructor
public class CreateTaskListController {

  private final CreateTaskListInputGateway createTasklistInputGateway;

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody TaskListRequest request) {
    createTasklistInputGateway.create(of(request));
    return ResponseEntity.noContent().build();
  }

}