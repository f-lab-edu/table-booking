package me.app.tablebooking.application.service;

import me.app.tablebooking.application.domain.model.Member;
import me.app.tablebooking.application.domain.model.Type;
import me.app.tablebooking.application.domain.service.AuthService;
import me.app.tablebooking.application.domain.service.JwtTokenProvider;
import me.app.tablebooking.application.port.in.LoginCommand;
import me.app.tablebooking.application.port.out.MemberPort;
import me.app.tablebooking.common.exception.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private MemberPort memberPort;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        memberPort = mock(MemberPort.class);
        jwtTokenProvider = mock(JwtTokenProvider.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthService(memberPort, jwtTokenProvider, passwordEncoder);
    }

    @Test
    void 로그인_성공한다() {
        // given
        String username = "john";
        String rawPassword = "password123";
        String encodedPassword = "encoded";
        Member member = new Member(1L, username, encodedPassword, Type.CUSTOMER, "John", "01012345678", null, null);

        LoginCommand command = new LoginCommand(username, rawPassword);

        when(memberPort.findByUsername(username)).thenReturn(member);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(jwtTokenProvider.createToken(eq(username), eq("CUSTOMER"))).thenReturn("mocked-token");

        // when
        String token = authService.login(command);

        // then
        assertEquals("mocked-token", token);
        verify(memberPort).findByUsername(username);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(jwtTokenProvider).createToken(username, "CUSTOMER");
    }

    @Test
    void 멤버가_존재하지_않으면_로그인_실패한다() {
        // given
        when(memberPort.findByUsername("john")).thenReturn(null);

        LoginCommand command = new LoginCommand("john", "wrongpass");

        // when & then
        assertThrows(InvalidCredentialsException.class, () -> authService.login(command));
    }

    @Test
    void 비밀번호가_일치하지_않으면_로그인_실패한다() {
        // given
        String username = "john";
        String rawPassword = "wrongpass";
        String encodedPassword = "encoded";

        Member member = new Member(1L, username, encodedPassword, Type.CUSTOMER, "John", "01012345678", null, null);

        when(memberPort.findByUsername(username)).thenReturn(member);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);

        LoginCommand command = new LoginCommand(username, rawPassword);

        // when & then
        assertThrows(InvalidCredentialsException.class, () -> authService.login(command));
    }
}
