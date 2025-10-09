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
import org.springframework.transaction.annotation.Transactional;

/**
 * class CreateTaskListUseCase
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class CreateTaskListUseCase implements CreateTaskListInputGateway {

  private final CreateTaskListOutputGateway outputGateway;

  @Transactional
  public TaskList create(TaskList tasklist, String userId) {
    var userExits = this.outputGateway.findUserById(userId)
      .isPresent();

    if (!userExits) {
      throw new TaskListUserNotFoundException();
    }

    tasklist.setUserId(userId);

    return outputGateway.create(tasklist);
  }

}