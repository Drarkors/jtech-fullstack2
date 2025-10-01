package br.com.jtech.tasklist.config.usecases.user;

import br.com.jtech.tasklist.adapters.database.user.AuthenticateUserAdapter;
import br.com.jtech.tasklist.application.core.usecases.user.AuthenticateUserUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * class CreateUserUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@Configuration
public class AuthenticateUserUseCaseConfig {


  @Bean
  public AuthenticateUserUseCase useCase(AuthenticateUserAdapter adapter, PasswordEncoder encoder,
                                         @Value("${security.token.secret}") String secretKey,
                                         @Value("${security.token.durationInMinutes}") Integer tokenDurationInMinutes) {
    return new AuthenticateUserUseCase(adapter, encoder, secretKey, tokenDurationInMinutes);
  }

}
