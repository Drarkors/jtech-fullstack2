package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.CreateTaskUseCase;
import br.com.jtech.tasklist.application.ports.output.task.CreateTaskOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class CreateTaskUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class CreateTaskUseCaseConfig {

  @Bean(name = "CreateTaskUseCase")
  public CreateTaskUseCase useCase(CreateTaskOutputGateway outputGateway) {
    return new CreateTaskUseCase(outputGateway);
  }

}
