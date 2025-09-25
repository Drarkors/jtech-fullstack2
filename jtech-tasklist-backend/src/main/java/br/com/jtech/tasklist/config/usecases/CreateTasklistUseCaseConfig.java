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
package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.database.CreateTaskListAdapter;
import br.com.jtech.tasklist.application.core.usecases.CreateTaskListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class TasklistUseCaseConfig
 * <p>
 * user angelo.vicente
 */
@Configuration
public class CreateTaskListUseCaseConfig {

  @Bean
  public CreateTaskListUseCase useCase(CreateTaskListAdapter createTasklistAdapter) {
    return new CreateTaskListUseCase(createTasklistAdapter);
  }

}