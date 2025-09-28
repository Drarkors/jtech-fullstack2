/*
 *  @(#)TasklistUseCase.java
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
package br.com.jtech.tasklist.application.core.usecases.tasklist;


import br.com.jtech.tasklist.application.core.entities.TaskList;
import br.com.jtech.tasklist.application.core.usecases.tasklist.exceptions.TaskListUserNotFoundException;
import br.com.jtech.tasklist.application.ports.input.tasklist.CreateTaskListInputGateway;
import br.com.jtech.tasklist.application.ports.output.tasklist.CreateTaskListOutputGateway;
import lombok.RequiredArgsConstructor;

/**
 * class CreateTaskListUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class CreateTaskListUseCase implements CreateTaskListInputGateway {

  private final CreateTaskListOutputGateway outputGateway;

  public TaskList create(TaskList tasklist) {
    var userExits = this.outputGateway.findUserById(tasklist.getUserId())
      .isPresent();

    if (!userExits) {
      throw new TaskListUserNotFoundException();
    }

    return outputGateway.create(tasklist);
  }

}