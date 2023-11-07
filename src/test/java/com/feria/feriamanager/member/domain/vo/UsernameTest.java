package com.feria.feriamanager.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 아이디 도메인")
class UsernameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("아이디 입력 - 아이디 미입력시 예외 발생")
    void nullAndEmptyUsername_throwException(String username) {
        // given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" test123", "test 123", "test123 "})
    @DisplayName("아이디 입력 - 아이디에 공백 입력시 예외 발생")
    void containSpaces_throwException(String username) {
        // given & when
        Throwable throwable = catchThrowable(() -> Username.of(username));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("아이디엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123", "user123", "member123"})
    @DisplayName("아이디 입력 - 올바른 형식의 아이디 입력시 아이디 객체 반환")
    void properUsernameFormat_returnUsernameObject(String username) {
        // given & when
        Username usernameObject = Username.of(username);

        // then
        assertThat(usernameObject).isInstanceOf(Username.class);
    }
}