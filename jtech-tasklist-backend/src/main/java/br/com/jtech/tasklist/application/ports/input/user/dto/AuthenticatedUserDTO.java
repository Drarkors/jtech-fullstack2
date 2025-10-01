package br.com.jtech.tasklist.application.ports.input.user.dto;

public record AuthenticatedUserDTO(String access_token, Long expires_in) {
}
