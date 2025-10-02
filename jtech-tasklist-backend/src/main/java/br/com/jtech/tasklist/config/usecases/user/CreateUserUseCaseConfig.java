package br.com.jtech.tasklist.config.usecases.user;

import br.com.jtech.tasklist.application.core.usecases.user.CreateUserUseCase;
import br.com.jtech.tasklist.application.ports.output.user.CreateUserOutputGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * class CreateUserUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class CreateUserUseCaseConfig {

  @Bean(name = "CreateUserUseCase")
  public CreateUserUseCase useCase(CreateUserOutputGateway outputGateway, PasswordEncoder passwordEncoder) {
    return new CreateUserUseCase(outputGateway, passwordEncoder);
  }

}
