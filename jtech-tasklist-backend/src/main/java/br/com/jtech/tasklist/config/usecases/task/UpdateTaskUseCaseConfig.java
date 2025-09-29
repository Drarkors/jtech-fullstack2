package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.adapters.database.task.UpdateTaskAdapter;
import br.com.jtech.tasklist.application.core.usecases.task.UpdateTaskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class UpdateTaskUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class UpdateTaskUseCaseConfig {

  @Bean
  public UpdateTaskUseCase useCase(UpdateTaskAdapter adapter) {
    return new UpdateTaskUseCase(adapter);
  }

}
