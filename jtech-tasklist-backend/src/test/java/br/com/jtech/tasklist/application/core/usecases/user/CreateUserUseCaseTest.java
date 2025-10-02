package br.com.jtech.tasklist.application.core.usecases.user;

import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.adapters.database.repositories.models.UserModel;
import br.com.jtech.tasklist.adapters.database.user.CreateUserAdapter;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.application.core.usecases.user.exceptions.UserAlreadyExistsException;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.user.CreateUserUseCaseConfig;
import br.com.jtech.tasklist.factories.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {

  @Mock
  UserRepository repository;

  @Mock
  PasswordEncoder encoder;

  @InjectMocks
  CreateUserAdapter adapter;

  CreateUserUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new CreateUserUseCaseConfig().useCase(this.adapter, this.encoder);
  }

  @Test
  @DisplayName("Should be able to create a user")
  void shouldCreateUser() {
    var user = UserFactory.fakeUserWithoutId();

    when(this.repository.findByUserName(eq(user.getUserName()))).thenReturn(Optional.empty());
    when(this.repository.save(any(UserModel.class))).thenAnswer((_p) -> {
      user.setId(GenId.newId());
      return user.toModel();
    });

    when(this.encoder.encode(any())).thenAnswer(invocation -> invocation.getArgument(0));

    var result = this.useCase.create(user);

    assertEquals(user.getId(), result.getId());
    assertEquals(user.getUserName(), result.getUserName());
    assertEquals(user.getPassword(), result.getPassword());

    verify(this.repository, times(1)).save(any());
  }

  @Test
  @DisplayName("Should not be able to create an user if it's userName is already registered")
  void shouldThrowUserAlreadyExistsException() {
    var user = User.builder()
      .userName("User")
      .password("Password")
      .build();

    when(this.repository.findByUserName(any())).thenReturn(Optional.of(user.toModel()));

    assertThrows(UserAlreadyExistsException.class, () -> this.useCase.create(user));
  }

}
