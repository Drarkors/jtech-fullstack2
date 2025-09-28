package br.com.jtech.tasklist.adapters.database.repositories;

import br.com.jtech.tasklist.adapters.database.repositories.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * class TaskRepository
 *
 * @author rafael.zanetti
 */
@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {

  Optional<TaskModel> findByNameAndTaskListId(String name, UUID taskListId);

}
