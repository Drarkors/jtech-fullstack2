package br.com.jtech.tasklist.config.infra.exceptions.constraints;

import br.com.jtech.tasklist.config.infra.utils.RegexUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UUIDConstraintValidator implements ConstraintValidator<UUIDConstraint, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || value.matches(RegexUtils.UUID_REGEX);
  }

}
