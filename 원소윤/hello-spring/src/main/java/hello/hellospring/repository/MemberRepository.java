package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
