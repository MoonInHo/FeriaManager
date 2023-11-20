package com.feria.feriamanager.member.application.service;

import com.feria.feriamanager.exception.exception.member.DuplicatePhoneException;
import com.feria.feriamanager.exception.exception.member.DuplicateUsernameException;
import com.feria.feriamanager.member.application.dto.CreateMemberRequestDto;
import com.feria.feriamanager.member.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유닛 테스트] - 회원 서비스")
class SignMemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SignMemberService signMemberService;

    @Test
    @DisplayName("아이디 중복 확인 - 아이디가 이미 존재할 경우 예외 발생")
    void duplicateUsername_throwException() {
        // given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "username123",
                "testPassword123!",
                "김이름",
                "010-1234-5678"
        );
        given(memberRepository.isUsernameExist(any())).willReturn(true);

        // when
        Throwable throwable = catchThrowable(() -> signMemberService.checkDuplicateUsername(createMemberRequestDto));

        // then
        assertThat(throwable).isInstanceOf(DuplicateUsernameException.class);
        assertThat(throwable).hasMessage("이미 존재하는 아이디입니다.");
    }

    @Test
    @DisplayName("연락처 중복 확인 - 연락처가 이미 존재할 경우 예외 발생")
    void duplicatePhone_throwException() {
        // given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "username123",
                "testPassword123!",
                "김이름",
                "010-1234-5678"
        );
        given(memberRepository.isPhoneExist(any())).willReturn(true);

        // when
        Throwable throwable = catchThrowable(() -> signMemberService.checkDuplicatePhone(createMemberRequestDto));

        // then
        assertThat(throwable).isInstanceOf(DuplicatePhoneException.class);
        assertThat(throwable).hasMessage("해당 연락처로 가입 정보가 존재합니다.");
    }

    @Test
    @DisplayName("회원 가입 - 올바른 형식의 정보로 회원가입 시도시 회원 생성")
    void properUserInfo_signUp_createMember() {
        // given
        CreateMemberRequestDto createMemberRequestDto = new CreateMemberRequestDto(
                "username123",
                "testPassword123!",
                "김이름",
                "010-1234-5678"
        );
        given(memberRepository.isUsernameExist(any())).willReturn(false);
        given(memberRepository.isPhoneExist(any())).willReturn(false);

        // when
        signMemberService.signUp(createMemberRequestDto);

        // then
        verify(memberRepository, times(1)).save(any());
    }
}