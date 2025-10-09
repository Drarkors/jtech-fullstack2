package br.com.jtech.tasklist.application.core.usecases.user;

import br.com.jtech.tasklist.application.ports.input.user.AuthenticateUserInputGateway;
import br.com.jtech.tasklist.application.ports.input.user.dto.AuthenticatedUserDTO;
import br.com.jtech.tasklist.application.ports.output.user.AuthenticateUserOutputGateway;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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
  private final String issuer;
  private final Integer durationInMinutes;

  @Transactional
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

    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    var expiresIn = Instant.now().plus(Duration.ofMinutes(this.durationInMinutes));

    var token = JWT.create()
      .withIssuer(this.issuer)
      .withExpiresAt(expiresIn)
      .withSubject(entity.getId())
      .sign(algorithm);

    return new AuthenticatedUserDTO(token, expiresIn.toEpochMilli());
  }

}
