/*
 *  @(#)TasklistAdapter.java
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
package br.com.jtech.tasklist.adapters.database;

import br.com.jtech.tasklist.adapters.database.repositories.TaskListRepository;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.ports.output.CreateTaskListOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * class CreateTaskListAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class CreateTaskListAdapter implements CreateTaskListOutputGateway {

  private final TaskListRepository repository;

  @Override
  public TaskList create(TaskList tasklist) {
    var entity = this.repository.save(tasklist.toModel());

    return TaskList.of(entity);
  }

}