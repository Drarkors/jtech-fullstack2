package br.com.jtech.tasklist.adapters.database.user;

import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.ports.output.user.CreateUserOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * class CreateUserAdapter
 * <p>
 * user rafael.zanetti
 */
@Component
@RequiredArgsConstructor
public class CreateUserAdapter implements CreateUserOutputGateway {

  private final UserRepository repository;

  @Override
  public User create(User user) {
    return User.of(this.repository.save(user.toModel()));
  }

  @Override
  public Optional<User> findByUserName(String userName) {
    var result = this.repository.findByUserName(userName);
    return result.map(User::of);
  }

}
