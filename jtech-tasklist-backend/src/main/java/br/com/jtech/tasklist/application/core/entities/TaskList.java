/*
 *  @(#)Tasklist.java
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
package br.com.jtech.tasklist.application.core.entities;

import br.com.jtech.tasklist.adapters.database.repositories.models.TaskListModel;
import br.com.jtech.tasklist.adapters.rest.protocols.TaskListRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;


/**
 * class Tasklist
 * <p>
 * user rafael.zanetti
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {

  private String id;
  private String name;
  private String description;
  private Integer order;

  public static List<TaskList> of(List<TaskListModel> entities) {
    return entities.stream().map(TaskList::of).toList();
  }

  public static TaskList of(TaskListModel entity) {
    return TaskList.builder()
      .id(entity.getId().toString())
      .name(entity.getName())
      .description(entity.getDescription())
      .order(entity.getOrder())
      .build();
  }

  public static TaskList of(TaskListRequest request) {
    return TaskList.builder()
      .id(request.getId())
      .build();
  }

  public TaskListModel toModel() {
    return TaskListModel.builder()
      .id(getId() != null ? UUID.fromString(getId()) : null)
      .name(getName())
      .description(getDescription())
      .order(getOrder())
      .build();
  }
}