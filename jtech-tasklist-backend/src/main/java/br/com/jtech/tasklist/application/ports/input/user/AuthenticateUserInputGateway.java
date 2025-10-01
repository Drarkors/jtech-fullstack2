package br.com.jtech.tasklist.application.ports.input.user;

import br.com.jtech.tasklist.application.ports.input.user.dto.AuthenticatedUserDTO;

import javax.naming.AuthenticationException;

/**
 * class AuthenticateUserInputGateway
 * <p>
 * user rafael.zanetti
 */
public interface AuthenticateUserInputGateway {

  AuthenticatedUserDTO authenticate(String userName, String password) throws AuthenticationException;

}
