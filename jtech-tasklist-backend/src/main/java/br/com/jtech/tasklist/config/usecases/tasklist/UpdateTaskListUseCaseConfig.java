package br.com.jtech.tasklist.config.usecases.tasklist;

import br.com.jtech.tasklist.application.core.usecases.tasklist.UpdateTaskListUseCase;
import br.com.jtech.tasklist.application.ports.output.tasklist.UpdateTaskListOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class UpdateTaskListUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class UpdateTaskListUseCaseConfig {

  @Bean(name = "UpdateTaskListUseCase")
  public UpdateTaskListUseCase useCase(UpdateTaskListOutputGateway outputGateWay) {
    return new UpdateTaskListUseCase(outputGateWay);
  }
}
