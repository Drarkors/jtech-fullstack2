/*
 *  @(#)TasklistRequest.java
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
package br.com.jtech.tasklist.adapters.rest.protocols.tasklist.requests.task;

import br.com.jtech.tasklist.application.core.entities.Task;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * class CreateTaskRequest
 * <p>
 * user rafael.zanetti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTaskRequest implements Serializable {

  @NotNull
  @NotEmpty
  @Size(min = 4, max = 100, message = "Field \"name\" must have from 4 up to 100 characters")
  private String name;

  @Size(max = 500, message = "Field \"description\" can have up to 500 characters")
  private String description;

  @NotNull
  @Max(value = Integer.MAX_VALUE, message = "Field \"order\" can go up to " + Integer.MAX_VALUE)
  private Integer order;

  @NotNull
  @NotEmpty
  private String taskListId;

  public Task to() {
    return Task.builder()
      .name(this.name)
      .description(this.description)
      .order(this.order)
      .taskListId(taskListId)
      .build();
  }

}