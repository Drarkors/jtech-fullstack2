package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.adapters.database.task.UpdateTasksOrderAdapter;
import br.com.jtech.tasklist.application.core.usecases.task.UpdateTasksOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class UpdateTasksOrderUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class UpdateTasksOrderUseCaseConfig {

  @Bean
  public UpdateTasksOrderUseCase useCase(UpdateTasksOrderAdapter adapter) {
    return new UpdateTasksOrderUseCase(adapter);
  }

}
