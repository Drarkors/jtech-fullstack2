package br.com.jtech.tasklist.config.usecases.user;

import br.com.jtech.tasklist.application.core.usecases.user.AuthenticateUserUseCase;
import br.com.jtech.tasklist.application.ports.output.user.AuthenticateUserOutputGateway;
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


  @Bean(name = "AuthenticateUserUseCase")
  public AuthenticateUserUseCase useCase(AuthenticateUserOutputGateway outputGateway, PasswordEncoder encoder,
                                         @Value("${security.token.secret}") String secretKey,
                                         @Value("${security.token.issuer}") String issuer,
                                         @Value("${security.token.durationInMinutes}") Integer tokenDurationInMinutes) {
    return new AuthenticateUserUseCase(outputGateway, encoder, secretKey, issuer, tokenDurationInMinutes);
  }

}
