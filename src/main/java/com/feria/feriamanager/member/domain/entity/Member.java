package com.feria.feriamanager.member.domain.entity;

import com.feria.feriamanager.member.domain.vo.Name;
import com.feria.feriamanager.member.domain.vo.Password;
import com.feria.feriamanager.member.domain.vo.Phone;
import com.feria.feriamanager.member.domain.vo.Username;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @Column(nullable = false, unique = true)
    private Username username;

    @Embedded
    @Column(nullable = false)
    private Password password;

    @Embedded
    @Column(nullable = false)
    private Name name;

    @Embedded
    @Column(nullable = false, unique = true)
    private Phone phone;

    private Member(Username username, Password password, Name name, Phone phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public static Member createMember(Username username, Password password, Name name, Phone phone) {
        return new Member(username, password, name, phone);
    }
}
