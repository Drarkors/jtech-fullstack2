package br.com.jtech.tasklist.application.core.usecases.user;

import br.com.jtech.tasklist.application.ports.input.user.AuthenticateUserInputGateway;
import br.com.jtech.tasklist.application.ports.input.user.dto.AuthenticatedUserDTO;
import br.com.jtech.tasklist.application.ports.output.user.AuthenticateUserOutputGateway;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

/**
 * class CreateUserUseCaseConfig
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class AuthenticateUserUseCase implements AuthenticateUserInputGateway {

  private final AuthenticateUserOutputGateway outputGateway;
  private final PasswordEncoder passwordEncoder;

  private final String secretKey;
  private final Integer tokenDurationInMinutes;

  @Override
  public AuthenticatedUserDTO authenticate(String userName, String password) throws AuthenticationException {
    var optional = this.outputGateway.findByUserName(userName);

    if (optional.isEmpty()) {
      throw new AuthenticationException();
    }

    var entity = optional.get();

    var passwordMatches = this.passwordEncoder.matches(password, entity.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expiresIn = Instant.now().plus(Duration.ofMinutes(tokenDurationInMinutes));

    var token = JWT.create()
      .withIssuer("tasklist-api")
      .withExpiresAt(expiresIn)
      .withSubject(entity.getId())
      .sign(algorithm);

    return new AuthenticatedUserDTO(token, expiresIn.toEpochMilli());
  }

}
