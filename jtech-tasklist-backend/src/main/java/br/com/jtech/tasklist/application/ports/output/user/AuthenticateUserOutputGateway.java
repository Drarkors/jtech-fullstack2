package br.com.jtech.tasklist.application.ports.output.user;

import br.com.jtech.tasklist.application.core.entities.User;

import java.util.Optional;

/**
 * class AuthenticateUserOutputGateway
 * <p>
 * user rafael.zanetti
 */
public interface AuthenticateUserOutputGateway {

  Optional<User> findByUserName(String userName);

}
