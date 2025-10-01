package br.com.jtech.tasklist.application.core.usecases.user;

import br.com.jtech.tasklist.adapters.database.user.CreateUserAdapter;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.user.exceptions.UserAlreadyExistsException;
import br.com.jtech.tasklist.application.ports.input.user.CreateUserInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * class CreateUserOutputGateway
 * <p>
 * user rafael.zanetti
 */
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserInputGateway {

  private final CreateUserAdapter adapter;

  private final PasswordEncoder passwordEncoder;

  @Override
  public User create(User user) {
    var optional = this.adapter.findByUserName(user.getUserName());

    if (optional.isPresent()) {
      throw new UserAlreadyExistsException();
    }

    var encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    return this.adapter.create(user);
  }

}
