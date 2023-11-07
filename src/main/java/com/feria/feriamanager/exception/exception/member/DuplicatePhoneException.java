package com.feria.feriamanager.exception.exception.member;

import com.feria.feriamanager.exception.exception.ApplicationException;
import com.feria.feriamanager.exception.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class DuplicatePhoneException extends ApplicationException {

    public DuplicatePhoneException() {
        super(HttpStatus.CONFLICT, ErrorCode.DUPLICATE_PHONE_ERROR, "해당 연락처로 가입 정보가 존재합니다.");
    }
}
