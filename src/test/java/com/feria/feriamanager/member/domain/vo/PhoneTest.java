package com.feria.feriamanager.member.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;

@DisplayName("[유닛 테스트] - 연락처 도메인")
class PhoneTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("연락처 입력 - 연락처 미입력시 예외 발생")
    void nullAndEmptyPhone_throwException(String phone) {
        // given & when
        Throwable throwable = catchThrowable(() -> Phone.of(phone));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("연락처를 입력하세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" 010-1234-5678", "010-1 234-5678", "010-1234-5678 "})
    @DisplayName("연락처 입력 - 연락처에 공백 입력시 예외 발생")
    void containSpaces_throwException(String phone) {
        // given & when
        Throwable throwable = catchThrowable(() -> Phone.of(phone));

        // then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage("연락처엔 공백을 포함할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"김개발", "김코딩", "김백", "김엔드"})
    @DisplayName("연락처 입력 - 올바른 형식의 연락처 입력시 연락처 객체 반환")
    void properPhoneFormat_returnPhoneObject(String phone) {
        // given & when
        Phone phoneObject = Phone.of(phone);

        // then
        assertThat(phoneObject).isInstanceOf(Phone.class);
    }
}