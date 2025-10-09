package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.DeleteTaskUseCase;
import br.com.jtech.tasklist.application.ports.output.task.DeleteTaskOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class DeleteTaskUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class DeleteTaskUseCaseConfig {

  @Bean(name = "DeleteTaskUseCase")
  public DeleteTaskUseCase useCase(DeleteTaskOutputGateway outputGateway) {
    return new DeleteTaskUseCase(outputGateway);
  }

}
