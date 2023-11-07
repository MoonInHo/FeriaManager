package com.feria.feriamanager.member.application.dto;

import com.feria.feriamanager.member.domain.entity.Member;
import com.feria.feriamanager.member.domain.vo.Name;
import com.feria.feriamanager.member.domain.vo.Password;
import com.feria.feriamanager.member.domain.vo.Phone;
import com.feria.feriamanager.member.domain.vo.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberRequestDto {

    private String username;
    private String password;
    private String name;
    private String phone;

    public Member toEntity() {
        return Member.createMember(
                Username.of(username),
                Password.of(password),
                Name.of(name),
                Phone.of(phone)
        );
    }
}
