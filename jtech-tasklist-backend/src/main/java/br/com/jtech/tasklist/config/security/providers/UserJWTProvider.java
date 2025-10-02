package br.com.jtech.tasklist.config.security.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserJWTProvider {

  private final String secretKey;

  public UserJWTProvider(@Value("${security.token.secret}") String secretKey) {
    this.secretKey = secretKey;
  }

  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer ", "");

    var algorithm = Algorithm.HMAC256(secretKey);

    try {
      return JWT.require(algorithm).build().verify(token);
    } catch (JWTVerificationException ex) {
      return null;
    }
  }

}
