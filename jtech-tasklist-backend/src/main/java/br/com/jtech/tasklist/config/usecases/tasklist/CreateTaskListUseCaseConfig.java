/*
 *  @(#)TasklistUseCaseConfig.java
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
package br.com.jtech.tasklist.config.usecases.tasklist;

import br.com.jtech.tasklist.adapters.database.tasklist.CreateTaskListAdapter;
import br.com.jtech.tasklist.application.core.usecases.tasklist.CreateTaskListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class TasklistUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class CreateTaskListUseCaseConfig {

  @Bean
  public CreateTaskListUseCase useCase(CreateTaskListAdapter createTasklistAdapter) {
    return new CreateTaskListUseCase(createTasklistAdapter);
  }

}