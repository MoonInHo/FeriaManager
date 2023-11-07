package com.feria.feriamanager.member.infrastructure.repository;

import com.feria.feriamanager.member.domain.vo.Phone;
import com.feria.feriamanager.member.domain.vo.Username;

public interface MemberQueryRepository {

    boolean isUsernameExist(Username username);

    boolean isPhoneExist(Phone phone);
}
