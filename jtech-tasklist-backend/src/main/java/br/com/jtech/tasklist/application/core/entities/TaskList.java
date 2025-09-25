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

import br.com.jtech.tasklist.adapters.database.repositories.entities.TaskListEntity;
import br.com.jtech.tasklist.adapters.rest.protocols.TasklistRequest;
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
 * user angelo.vicente
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {
  private String id;

  public static List<TaskList> of(List<TaskListEntity> entities) {
    return entities.stream().map(TaskList::of).toList();
  }

  public static TaskList of(TaskListEntity entity) {
    return TaskList.builder()
      .id(entity.getId().toString())
      .build();
  }

  public static TaskList of(TasklistRequest request) {
    return TaskList.builder()
      .id(request.getId())
      .build();
  }

  public TaskListEntity toEntity() {
    return TaskListEntity.builder()
      .id(UUID.fromString(getId()))
      .build();
  }
}