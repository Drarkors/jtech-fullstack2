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
import br.com.jtech.tasklist.config.infra.exceptions.constraints.UUIDConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@EqualsAndHashCode(exclude = "tasks")
@NoArgsConstructor
@AllArgsConstructor
public class TaskList {

  @NotNull
  @NotEmpty
  @UUIDConstraint
  private String id;

  @NotNull
  @NotEmpty
  @UUIDConstraint
  private String userId;

  @NotNull
  @NotEmpty
  @Size(min = 4, max = 100, message = "Field \"name\" must have from 4 up to 100 characters")
  private String name;

  @Size(max = 500, message = "Field \"description\" can have up to 500 characters")
  private String description;

  @NotNull
  @Max(value = Integer.MAX_VALUE, message = "Field \"order\" can go up to " + Integer.MAX_VALUE)
  private Integer order;

  @Builder.Default
  private Set<Task> tasks = new HashSet<>();

  public static List<TaskList> of(List<TaskListModel> entities) {
    return entities.stream().map(TaskList::of).toList();
  }

  public static TaskList of(TaskListModel entity) {
    return TaskList.builder()
      .id(entity.getId().toString())
      .userId(entity.getUserId().toString())
      .name(entity.getName())
      .description(entity.getDescription())
      .order(entity.getOrder())
      .build();
  }

  public TaskListModel toModel() {
    return TaskListModel.builder()
      .id(getId() != null ? UUID.fromString(getId()) : null)
      .userId(UUID.fromString(getUserId()))
      .name(getName())
      .description(getDescription())
      .order(getOrder())
      .build();
  }
}