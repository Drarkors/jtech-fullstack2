package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.database.tasklist.FetchUserTaskListsAdapter;
import br.com.jtech.tasklist.application.core.usecases.tasklist.FetchUserTaskListsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class TasklistUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class FetchUserTaskListsUseCaseConfig {

  @Bean
  public FetchUserTaskListsUseCase useCase(FetchUserTaskListsAdapter adapter) {
    return new FetchUserTaskListsUseCase(adapter);
  }

}
