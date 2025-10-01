package br.com.jtech.tasklist.application.ports.input.user;

import br.com.jtech.tasklist.application.core.entities.User;

/**
 * class CreateUserInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface CreateUserInputGateway {

  User create(User user);

}
