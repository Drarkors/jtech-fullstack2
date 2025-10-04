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
package br.com.jtech.tasklist.adapters.rest.protocols.tasklist.responses;

import br.com.jtech.tasklist.adapters.database.repositories.models.TaskListModel;
import br.com.jtech.tasklist.application.core.entities.TaskList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * class TaskListResponse
 * <p>
 * user rafael.zanetti
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskListResponse implements Serializable {
  List<TaskListResponse> responses;
  private String id;

  public static TaskListResponse of(TaskList tasklist) {
    return TaskListResponse.builder()
      .id(tasklist.getId())
      .build();
  }

  public static TaskListResponse of(List<TaskListModel> entities) {
    var list = entities.stream().map(TaskListResponse::of).toList();
    return TaskListResponse.builder()
      .responses(list)
      .build();
  }

  public static TaskListResponse of(TaskListModel entity) {
    var response = new TaskListResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }
}