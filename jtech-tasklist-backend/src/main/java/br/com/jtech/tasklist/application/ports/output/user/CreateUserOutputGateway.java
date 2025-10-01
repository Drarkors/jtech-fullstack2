package br.com.jtech.tasklist.application.ports.output.user;

import br.com.jtech.tasklist.application.core.entities.User;

import java.util.Optional;

/**
 * class CreateUserOutputGateway
 * <p>
 * user rafael.zanetti
 */
public interface CreateUserOutputGateway {

  User create(User user);

  Optional<User> findByUserName(String userName);

}
