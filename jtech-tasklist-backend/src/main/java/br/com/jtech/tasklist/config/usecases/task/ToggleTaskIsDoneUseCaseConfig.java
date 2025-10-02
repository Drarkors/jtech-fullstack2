package br.com.jtech.tasklist.config.usecases.task;

import br.com.jtech.tasklist.application.core.usecases.task.ToggleTaskIsDoneUseCase;
import br.com.jtech.tasklist.application.ports.output.task.ToggleTaskIsDoneOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class ToggleTaskIsDoneUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class ToggleTaskIsDoneUseCaseConfig {

  @Bean(name = "ToggleTaskIsDoneUseCase")
  public ToggleTaskIsDoneUseCase useCase(ToggleTaskIsDoneOutputGateway outputGateway) {
    return new ToggleTaskIsDoneUseCase(outputGateway);
  }

}
