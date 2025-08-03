package me.app.tablebooking.application.service;

import me.app.tablebooking.application.domain.model.Type;
import me.app.tablebooking.application.domain.service.MemberService;
import me.app.tablebooking.application.port.in.SignUpCommand;
import me.app.tablebooking.application.port.out.MemberPort;
import me.app.tablebooking.common.exception.DuplicatedUsernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;
import org.springframework.dao.DataIntegrityViolationException;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private MemberPort memberPort;
    private PasswordEncoder passwordEncoder;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        memberPort = mock(MemberPort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        memberService = new MemberService(memberPort, passwordEncoder);
    }

    @Test
    void 회원가입_성공() {
        // given
        SignUpCommand command = new SignUpCommand(
                "johndoe",
                "plainPass",
                "john",
                "010-1234-5678",
                Type.CUSTOMER
        );
        String encodedPassword = "hashedPass";

        when(passwordEncoder.encode("plainPass")).thenReturn(encodedPassword);

        // when
        memberService.signUp(command);

        // then
        verify(passwordEncoder).encode("plainPass");
        verify(memberPort).save(argThat(member ->
                member.getUsername().equals("johndoe") &&
                        member.getPassword().equals("hashedPass") &&
                        member.getType() == Type.CUSTOMER &&
                        member.getName().equals("John") &&
                        member.getPhoneNumber().equals("01012345678") &&
                        member.getId() == null
        ));
    }

    @Test
    void 유저네임_겹치면_에러를_던진다() {
        // given
        SignUpCommand command = new SignUpCommand(
                "johndoe",
                "plainPass",
                "john",
                "010-1234-5678",
                Type.CUSTOMER
        );
        when(passwordEncoder.encode("plainPass")).thenReturn("hashedPass");
        doThrow(new DataIntegrityViolationException("duplicate")).when(memberPort).save(any());

        // when & then
        assertThrows(DuplicatedUsernameException.class, () -> memberService.signUp(command));
    }
}
