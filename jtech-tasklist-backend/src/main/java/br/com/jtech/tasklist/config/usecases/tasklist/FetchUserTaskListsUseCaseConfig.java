package br.com.jtech.tasklist.config.usecases.tasklist;

import br.com.jtech.tasklist.application.core.usecases.tasklist.FetchUserTaskListsUseCase;
import br.com.jtech.tasklist.application.ports.output.tasklist.FetchUserTaskListsOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class TasklistUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class FetchUserTaskListsUseCaseConfig {

  @Bean(name = "FetchUserTaskListsUseCase")
  public FetchUserTaskListsUseCase useCase(FetchUserTaskListsOutputGateway outputGateway) {
    return new FetchUserTaskListsUseCase(outputGateway);
  }

}
