package com.feria.feriamanager.exception.exception.member;

import com.feria.feriamanager.exception.exception.ApplicationException;
import com.feria.feriamanager.exception.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends ApplicationException {

    public DuplicateUsernameException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_USERNAME_ERROR, "이미 존재하는 아이디입니다.");
    }
}
