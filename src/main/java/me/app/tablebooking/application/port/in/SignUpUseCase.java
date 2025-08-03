package me.app.tablebooking.application.port.in;

import me.app.tablebooking.application.domain.model.Member;

public interface SignUpUseCase {
    void signUp(SignUpCommand command);
}
