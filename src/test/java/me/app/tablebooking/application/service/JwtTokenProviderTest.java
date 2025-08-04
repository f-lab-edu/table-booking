package me.app.tablebooking.application.service;

import me.app.tablebooking.application.domain.service.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider jwtTokenProvider;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        jwtTokenProvider = new JwtTokenProvider();

        Field field = JwtTokenProvider.class.getDeclaredField("secretKeyRaw");
        field.setAccessible(true);
        field.set(jwtTokenProvider, "test-secret-key-012947285683474293");

        jwtTokenProvider.init();
    }

    @Test
    void jwt토큰을_성공적으로_만든다() {
        // given
        String username = "johndoe";
        String role = "CUSTOMER";

        // when
        String token = jwtTokenProvider.createToken(username, role);

        // then
        assertThat(token).isNotBlank();
        assertThat(jwtTokenProvider.validateToken(token)).isTrue();
        assertThat(jwtTokenProvider.getUsername(token)).isEqualTo(username);
    }

    @Test
    void 잘못된_토큰이_오면_실패한다() {
        // given
        String invalidToken = "this.is.not.valid";

        // when
        boolean result = jwtTokenProvider.validateToken(invalidToken);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 토큰을_받고_적절한_유저네임을_반환한다() {
        // given
        String token = jwtTokenProvider.createToken("alice", "OWNER");

        // when
        String username = jwtTokenProvider.getUsername(token);

        // then
        assertThat(username).isEqualTo("alice");
    }
}

