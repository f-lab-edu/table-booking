package me.app.tablebooking.application.port.in;

import jakarta.validation.constraints.NotBlank;
import me.app.tablebooking.application.domain.model.Type;

public record SignUpCommand(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String name,
        @NotBlank String phoneNumber,
        Type type
) {
}