package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.database.tasklist.UpdateTaskListAdapter;
import br.com.jtech.tasklist.application.core.usecases.tasklist.UpdateTaskListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class UpdateTaskListUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class UpdateTaskListUseCaseConfig {

  @Bean
  public UpdateTaskListUseCase useCase(UpdateTaskListAdapter adapter) {
    return new UpdateTaskListUseCase(adapter);
  }
}
