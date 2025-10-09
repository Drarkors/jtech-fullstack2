package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.GetTaskByIdUseCase;
import br.com.jtech.tasklist.application.ports.output.task.GetTaskByIdOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class GetTaskByIdUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class GetTaskByIdUseCaseConfig {

  @Bean(name = "GetTaskByIdUseCase")
  public GetTaskByIdUseCase useCase(GetTaskByIdOutputGateway outputGateway) {
    return new GetTaskByIdUseCase(outputGateway);
  }

}
