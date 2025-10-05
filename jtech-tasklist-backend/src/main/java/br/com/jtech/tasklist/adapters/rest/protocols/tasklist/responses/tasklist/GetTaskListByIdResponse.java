/*
 *  @(#)TasklistResponse.java
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
package br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.tasklist;

import br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses.task.GetTaskByIdResponse;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * class GetTaskListByIdResponse
 * <p>
 * user rafael.zanetti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTaskListByIdResponse implements Serializable {

  private String id;
  private String name;
  private String description;
  private Integer order;
  private Set<GetTaskByIdResponse> tasks;

  public static GetTaskListByIdResponse of(TaskList entity) {
    return GetTaskListByIdResponse.builder()
      .id(entity.getId())
      .name(entity.getName())
      .description(entity.getDescription())
      .order(entity.getOrder())
      .tasks(entity.getTasks().stream().map(GetTaskByIdResponse::of).collect(Collectors.toSet()))
      .build();
  }

}