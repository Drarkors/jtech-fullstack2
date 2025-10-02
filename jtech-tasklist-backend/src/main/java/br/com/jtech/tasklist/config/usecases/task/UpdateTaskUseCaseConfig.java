package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.UpdateTaskUseCase;
import br.com.jtech.tasklist.application.ports.output.task.UpdateTaskOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class UpdateTaskUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class UpdateTaskUseCaseConfig {

  @Bean(name = "UpdateTaskUseCase")
  public UpdateTaskUseCase useCase(UpdateTaskOutputGateway outputGateway) {
    return new UpdateTaskUseCase(outputGateway);
  }

}
