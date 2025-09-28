package br.com.jtech.tasklist.adapters.database.repositories.models;

import br.com.jtech.tasklist.application.core.entities.TaskList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * class TaskModel
 *
 * @author rafael.zanetti
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "task")
@Table(name = "task", uniqueConstraints = {
  @UniqueConstraint(name = "unique_task_per_list", columnNames = {"name", "task_list_id"})
})
public class TaskModel {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @Column(name = "task_list_id", nullable = false)
  private UUID taskListId;

  @Column(nullable = false, length = 100)
  @Size(max = 100, min = 1)
  private String name;

  @Column(length = 500)
  @Size(max = 500)
  private String description;

  @Column
  @Min(0)
  @Max(Integer.MAX_VALUE)
  @ColumnDefault(value = "0")
  private Integer order;

  @Column(name = "is_done")
  @ColumnDefault(value = "false")
  private Boolean isDone;

  @Column
  @ColumnDefault(value = "false")
  private boolean isDeleted;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "task_list_id", updatable = false, insertable = false)
  private TaskList taskList;
}
