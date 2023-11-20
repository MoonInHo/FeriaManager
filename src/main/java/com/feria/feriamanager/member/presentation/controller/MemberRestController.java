package com.feria.feriamanager.member.presentation.controller;

import com.feria.feriamanager.member.application.service.SignMemberService;
import com.feria.feriamanager.member.application.dto.CreateMemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberRestController {

    private final SignMemberService signMemberService;

    @GetMapping("/main")
    public ResponseEntity<String> mainPage() {
        return ResponseEntity.ok("mainPage");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @RequestBody CreateMemberRequestDto createMemberRequestDto
    ) {
        signMemberService.signUp(createMemberRequestDto);
        return ResponseEntity.ok().build();
    }
}
