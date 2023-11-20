package com.feria.feriamanager.member.infrastructure.repository;

import com.feria.feriamanager.member.domain.entity.Member;
import com.feria.feriamanager.member.domain.vo.Phone;
import com.feria.feriamanager.member.domain.vo.Username;

import java.util.Optional;

public interface MemberQueryRepository {

    boolean isUsernameExist(Username username);

    boolean isPhoneExist(Phone phone);

    Optional<Member> getMember(Username username);
}
