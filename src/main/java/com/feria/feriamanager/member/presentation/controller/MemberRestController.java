package com.feria.feriamanager.member.presentation.controller;

import com.feria.feriamanager.member.application.service.SignMemberService;
import com.feria.feriamanager.member.application.dto.CreateMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final SignMemberService signMemberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody CreateMemberRequestDto createMemberRequestDto
    ) {
        signMemberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }
}
