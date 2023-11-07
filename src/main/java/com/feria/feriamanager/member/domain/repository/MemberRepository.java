package com.feria.feriamanager.member.domain.repository;

import com.feria.feriamanager.member.domain.entity.Member;
import com.feria.feriamanager.member.infrastructure.repository.MemberQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryRepository {
}
