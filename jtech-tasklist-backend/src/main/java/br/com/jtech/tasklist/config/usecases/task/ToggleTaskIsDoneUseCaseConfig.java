package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.adapters.database.task.ToggleTaskIsDoneAdapter;
import br.com.jtech.tasklist.application.core.usecases.task.ToggleTaskIsDoneUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class ToggleTaskIsDoneUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class ToggleTaskIsDoneUseCaseConfig {

  @Bean
  public ToggleTaskIsDoneUseCase useCase(ToggleTaskIsDoneAdapter adapter) {
    return new ToggleTaskIsDoneUseCase(adapter);
  }

}
