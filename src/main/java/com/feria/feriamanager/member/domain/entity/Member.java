package com.feria.feriamanager.member.domain.entity;

import com.feria.feriamanager.member.domain.vo.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

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

    @Embedded
    @Column(nullable = false)
    private Role role;

    private Member(Username username, Password password, Name name, Phone phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = Role.of("ROLE_USER");
    }

    public static Member createMember(Username username, Password password, Name name, Phone phone) {
        return new Member(username, password, name, phone);
    }

    public List<GrantedAuthority> createRole() {
        return Collections.singletonList(role.createRole());
    }

    public void passwordEncrypt(PasswordEncoder passwordEncoder) {
        this.password = password.encodedPassword(passwordEncoder);
    }

    public String username() {
        return username.username();
    }

    public String password() {
        return password.password();
    }
}
