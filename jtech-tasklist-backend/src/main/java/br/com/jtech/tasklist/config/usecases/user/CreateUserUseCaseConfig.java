package br.com.jtech.tasklist.config.usecases.user;

import br.com.jtech.tasklist.adapters.database.user.CreateUserAdapter;
import br.com.jtech.tasklist.application.core.usecases.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
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

  @Bean
  public CreateUserUseCase useCase(CreateUserAdapter adapter, PasswordEncoder passwordEncoder) {
    return new CreateUserUseCase(adapter, passwordEncoder);
  }

}
