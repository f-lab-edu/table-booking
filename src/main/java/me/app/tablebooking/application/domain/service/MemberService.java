package me.app.tablebooking.application.domain.service;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.application.domain.model.Member;
import me.app.tablebooking.application.port.in.SignUpCommand;
import me.app.tablebooking.application.port.in.SignUpUseCase;
import me.app.tablebooking.application.port.out.MemberPort;
import me.app.tablebooking.common.exception.DuplicatedUsernameException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService implements SignUpUseCase {
    private final MemberPort memberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpCommand command) {
        String encoded = passwordEncoder.encode(command.password());

        Member member = new Member(
                null,
                command.username(),
                encoded,
                command.type(),
                command.name(),
                command.phoneNumber(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        try {
            memberPort.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedUsernameException();
        }
    }
}
