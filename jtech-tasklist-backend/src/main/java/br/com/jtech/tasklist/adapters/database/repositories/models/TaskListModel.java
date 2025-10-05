/*
 *  @(#)TasklistEntity.java
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
package br.com.jtech.tasklist.adapters.database.repositories.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * class TaskListModel
 *
 * @author rafael.zanetti
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task_list")
@Table(name = "task_list", indexes = {
  @Index(columnList = "id"),
  @Index(name = "deletedIndex", columnList = "id, is_deleted")
})
@SQLDelete(sql = "UPDATE task_list SET is_deleted = true WHERE id=?")
@SQLRestriction("is_deleted = false")
public class TaskListModel {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @Column(nullable = false, length = 100)
  @Size(max = 100, min = 1)
  private String name;

  @Column(length = 500)
  @Size(max = 500)
  private String description;

  @Column(name = "at_order")
  @Min(0)
  @Max(Integer.MAX_VALUE)
  @ColumnDefault(value = "0")
  private Integer order;

  @Column(name = "is_deleted")
  @ColumnDefault(value = "false")
  @Builder.Default
  private boolean isDeleted = false;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_id", updatable = false, insertable = false)
  private UserModel user;

}