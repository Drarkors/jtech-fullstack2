package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.UpdateTasksOrderUseCase;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTasksOrderOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class UpdateTasksOrderUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class UpdateTasksOrderUseCaseConfig {

  @Bean(name = "UpdateTasksOrderUseCase")
  public UpdateTasksOrderUseCase useCase(UpdateTasksOrderOutputGateway outputGateway) {
    return new UpdateTasksOrderUseCase(outputGateway);
  }

}
