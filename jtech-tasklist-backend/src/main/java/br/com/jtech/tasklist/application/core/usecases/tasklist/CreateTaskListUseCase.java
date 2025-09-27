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
import br.com.jtech.tasklist.application.ports.input.CreateTaskListInputGateway;
import br.com.jtech.tasklist.application.ports.output.CreateTaskListOutputGateway;

/**
 * class CreateTaskListUseCase
 * <p>
 * user rafael.zanetti
 */
public class CreateTaskListUseCase implements CreateTaskListInputGateway {

  private final CreateTaskListOutputGateway createTasklistOutputGateway;

  public CreateTaskListUseCase(CreateTaskListOutputGateway createTasklistOutputGateway) {
    this.createTasklistOutputGateway = createTasklistOutputGateway;
  }

  public TaskList create(TaskList tasklist) {
    return createTasklistOutputGateway.create(tasklist);
  }

}