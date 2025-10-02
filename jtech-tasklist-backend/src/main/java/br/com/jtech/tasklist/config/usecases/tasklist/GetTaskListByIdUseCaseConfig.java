package br.com.jtech.tasklist.config.usecases.tasklist;

import br.com.jtech.tasklist.application.core.usecases.tasklist.GetTaskListByIdUseCase;
import br.com.jtech.tasklist.application.ports.output.tasklist.GetTaskListByIdOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * class GetTaskListByIdUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class GetTaskListByIdUseCaseConfig {

  @Bean(name = "GetTaskListByIdUseCase")
  public GetTaskListByIdUseCase useCase(GetTaskListByIdOutputGateway outputGateWay) {
    return new GetTaskListByIdUseCase(outputGateWay);
  }

}
