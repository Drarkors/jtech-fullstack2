package br.com.jtech.tasklist.application.core.usecases.user;

import br.com.jtech.tasklist.adapters.database.repositories.UserRepository;
import br.com.jtech.tasklist.adapters.database.user.AuthenticateUserAdapter;
import br.com.jtech.tasklist.application.core.entities.User;
import br.com.jtech.tasklist.config.infra.utils.GenId;
import br.com.jtech.tasklist.config.usecases.user.AuthenticateUserUseCaseConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.AuthenticationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticateUserUseCaseTest {

  @Mock
  UserRepository repository;

  @Mock
  PasswordEncoder encoder;

  @InjectMocks
  AuthenticateUserAdapter adapter;

  AuthenticateUserUseCase useCase;

  @BeforeEach
  void setup() {
    this.useCase = new AuthenticateUserUseCaseConfig().useCase(adapter, encoder, "SECRET", 10);
  }

  @Test
  @DisplayName("Should be able to authenticate an user and return an access token")
  void shouldAuthenticateUser() throws AuthenticationException {
    var user = User.builder()
      .id(GenId.newId())
      .userName("User")
      .password("Password")
      .build();

    when(this.repository.findByUserName(eq(user.getUserName()))).thenReturn(Optional.of(user.toModel()));

    when(this.encoder.matches(any(), any())).thenReturn(true);

    var result = this.useCase.authenticate(user.getUserName(), user.getPassword());

    assertNotNull(result.access_token());
    assertNotNull(result.expires_in());
  }

  @Test
  @DisplayName("Should not be able to authenticate an user if user isn't found")
  void shouldThrowAuthenticationExceptionIfNotFound() {
    var user = User.builder()
      .id(GenId.newId())
      .userName("User")
      .password("Password")
      .build();

    when(this.repository.findByUserName(eq(user.getUserName()))).thenReturn(Optional.empty());

    assertThrows(AuthenticationException.class, () -> this.useCase.authenticate(user.getUserName(), user.getPassword()));
  }

  @Test
  @DisplayName("Should not be able to authenticate an user if password doesn't match")
  void shouldThrowAuthenticationExceptionIfPasswordMismatches() {
    var user = User.builder()
      .id(GenId.newId())
      .userName("User")
      .password("Password")
      .build();

    when(this.repository.findByUserName(eq(user.getUserName()))).thenReturn(Optional.of(user.toModel()));

    when(this.encoder.matches(any(), any())).thenReturn(false);

    assertThrows(AuthenticationException.class, () -> this.useCase.authenticate(user.getUserName(), user.getPassword()));
  }

}
