package me.app.tablebooking.application.port.in;

import jakarta.validation.constraints.NotBlank;

public record LoginCommand(
        @NotBlank String username,
        @NotBlank String password
) {
}