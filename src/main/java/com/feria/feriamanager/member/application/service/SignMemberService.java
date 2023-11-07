package com.feria.feriamanager.member.application.service;

import com.feria.feriamanager.exception.exception.member.DuplicateUsernameException;
import com.feria.feriamanager.exception.exception.member.DuplicatePhoneException;
import com.feria.feriamanager.member.application.dto.CreateMemberRequestDto;
import com.feria.feriamanager.member.domain.repository.MemberRepository;
import com.feria.feriamanager.member.domain.vo.Phone;
import com.feria.feriamanager.member.domain.vo.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(CreateMemberRequestDto createMemberRequestDto) {

        checkDuplicateUsername(createMemberRequestDto);
        checkDuplicatePhone(createMemberRequestDto);

        memberRepository.save(createMemberRequestDto.toEntity());
    }

    @Transactional(readOnly = true)
    protected void checkDuplicateUsername(CreateMemberRequestDto createMemberRequestDto) {
        boolean usernameExist = memberRepository.isUsernameExist(Username.of(createMemberRequestDto.getUsername()));
        if (usernameExist) {
            throw new DuplicateUsernameException();
        }
    }

    @Transactional(readOnly = true)
    protected void checkDuplicatePhone(CreateMemberRequestDto createMemberRequestDto) {
        boolean phoneExist = memberRepository.isPhoneExist(Phone.of(createMemberRequestDto.getPhone()));
        if (phoneExist) {
            throw new DuplicatePhoneException();
        }
    }
}
