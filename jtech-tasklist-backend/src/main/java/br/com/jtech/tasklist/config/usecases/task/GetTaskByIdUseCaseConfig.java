package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.adapters.database.task.GetTaskByIdAdapter;
import br.com.jtech.tasklist.application.core.usecases.task.GetTaskByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class CreateTaskUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class GetTaskByIdUseCaseConfig {

  @Bean
  public GetTaskByIdUseCase useCase(GetTaskByIdAdapter adapter) {
    return new GetTaskByIdUseCase(adapter);
  }

}
