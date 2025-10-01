package br.com.jtech.tasklist.application.core.usecases.user;

import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.user.exceptions.UserAlreadyExistsException;
import br.com.jtech.tasklist.application.ports.input.user.CreateUserInputGateway;
import br.com.jtech.tasklist.application.ports.output.user.CreateUserOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * class CreateUserOutputGateway
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserInputGateway {

  private final CreateUserOutputGateway outputGateway;

  private final PasswordEncoder passwordEncoder;

  @Override
  public User create(User user) {
    var optional = this.outputGateway.findByUserName(user.getUserName());

    if (optional.isPresent()) {
      throw new UserAlreadyExistsException();
    }

    var encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    return this.outputGateway.create(user);
  }

}
