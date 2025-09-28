package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.database.task.CreateTaskAdapter;
import br.com.jtech.tasklist.application.core.usecases.task.CreateTaskUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class CreateTaskUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class CreateTaskUseCaseConfig {

  @Bean
  public CreateTaskUseCase useCase(CreateTaskAdapter createTaskAdapter) {
    return new CreateTaskUseCase(createTaskAdapter);
  }

}
