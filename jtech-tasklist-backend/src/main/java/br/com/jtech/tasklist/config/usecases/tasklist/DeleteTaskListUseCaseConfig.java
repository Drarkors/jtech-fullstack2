package br.com.jtech.tasklist.config.usecases.tasklist;

import br.com.jtech.tasklist.application.core.usecases.tasklist.DeleteTaskListUseCase;
import br.com.jtech.tasklist.application.ports.output.tasklist.DeleteTaskListOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class DeleteTaskListUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class DeleteTaskListUseCaseConfig {

  @Bean(name = "DeleteTaskListUseCase")
  public DeleteTaskListUseCase useCase(DeleteTaskListOutputGateway outputGateway) {
    return new DeleteTaskListUseCase(outputGateway);
  }

}
