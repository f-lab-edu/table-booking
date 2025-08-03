package me.app.tablebooking.application.domain.service;

import lombok.RequiredArgsConstructor;
import me.app.tablebooking.application.domain.model.Member;
import me.app.tablebooking.application.port.in.LoginCommand;
import me.app.tablebooking.application.port.in.LoginUseCase;
import me.app.tablebooking.application.port.out.MemberPort;
import me.app.tablebooking.common.exception.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase {
    private final MemberPort memberPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public String login(LoginCommand loginCommand) {
        Member member = memberPort.findByUsername(loginCommand.username());

        if (member == null ||(!passwordEncoder.matches(loginCommand.password(), member.getPassword()))) {
            throw new InvalidCredentialsException();
        }

        String token = jwtTokenProvider.createToken(member.getUsername(), member.getType().name());
        return token;
    }
}
