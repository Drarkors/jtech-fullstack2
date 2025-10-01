package br.com.jtech.tasklist.application.core.entities;

import br.com.jtech.tasklist.adapters.database.repositories.models.UserModel;
import br.com.jtech.tasklist.config.infra.exceptions.constraints.UUIDConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @UUIDConstraint
  private String id;

  @NotNull
  @NotEmpty
  @Size(min = 4, max = 30, message = "Field \"userName\" must have from 4 up to 30 characters")
  private String userName;

  @NotNull
  @NotEmpty
  @Size(min = 4, max = 30, message = "Field \"password\" must have from 4 up to 30 characters")
  private String password;

  public static User of(UserModel entity) {
    return User.builder()
      .id(entity.getId() != null ? entity.getId().toString() : null)
      .userName(entity.getUserName())
      .password(entity.getPassword())
      .build();
  }

  public UserModel toModel() {
    return UserModel.builder()
      .id(getId() != null ? UUID.fromString(getId()) : null)
      .userName(getUserName())
      .password(getPassword())
      .build();
  }

}
