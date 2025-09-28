package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.database.tasklist.GetTaskListByIdAdapter;
import br.com.jtech.tasklist.application.core.usecases.tasklist.GetTaskListByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class GetTaskListByIdUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class GetTaskListByIdUseCaseConfig {

  @Bean
  public GetTaskListByIdUseCase useCase(GetTaskListByIdAdapter adapter) {
    return new GetTaskListByIdUseCase(adapter);
  }

}
