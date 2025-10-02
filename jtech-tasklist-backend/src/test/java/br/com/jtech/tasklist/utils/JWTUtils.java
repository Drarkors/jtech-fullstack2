package br.com.jtech.tasklist.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class JWTUtils {

  private final String secret;
  private final String issuer;
  private final Integer durationInMinutes;

  public JWTUtils(@Value("${security.token.secret}") String secret,
                  @Value("${security.token.issuer}") String issuer,
                  @Value("${security.token.durationInMinutes}") Integer durationInMinutes) {
    this.secret = secret;
    this.issuer = issuer;
    this.durationInMinutes = durationInMinutes;
  }

  public String generateToken(String id) {
    Algorithm algorithm = Algorithm.HMAC256(this.secret);

    var expiresIn = Instant.now().plus(Duration.ofMinutes(this.durationInMinutes));

    return JWT.create()
      .withIssuer(this.issuer)
      .withExpiresAt(expiresIn)
      .withSubject(id)
      .sign(algorithm);
  }
}
