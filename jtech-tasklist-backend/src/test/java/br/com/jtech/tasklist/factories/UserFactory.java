package br.com.jtech.tasklist.factories;

import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.config.infra.utils.GenId;

public class UserFactory {

  private UserRepository repository;

  public UserFactory() {
  }

  public UserFactory(UserRepository repository) {
    this.repository = repository;
  }

  public User makeUser() {
    var entity = User.builder()
      .userName("VALID_USERNAME")
      .password("!P@SSW0rD")
      .build();

    return User.of(this.repository.saveAndFlush(entity.toModel()));
  }

  public static User fakeUserWithoutId() {
    return User.builder()
      .userName("User")
      .password("Password")
      .build();
  }

  public static User fakeUser() {
    return User.builder()
      .id(GenId.newId())
      .userName("User")
      .password("Password")
      .build();
  }

}
