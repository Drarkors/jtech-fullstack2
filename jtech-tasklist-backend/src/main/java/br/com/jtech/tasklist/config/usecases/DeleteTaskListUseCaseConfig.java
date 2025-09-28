package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.database.tasklist.DeleteTaskListAdapter;
import br.com.jtech.tasklist.application.core.usecases.tasklist.DeleteTaskListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class DeleteTaskListUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class DeleteTaskListUseCaseConfig {

  @Bean
  public DeleteTaskListUseCase useCase(DeleteTaskListAdapter adapter) {
    return new DeleteTaskListUseCase(adapter);
  }

}
