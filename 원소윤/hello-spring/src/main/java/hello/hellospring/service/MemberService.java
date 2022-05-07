package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원 가입
    public long join (Member member){
        //같은 이름이 있는 중복 회원은 안된다.
        validateDuplicateMember(member); //중복 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{ //만약 멤버(m, 값)가 존재하면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> fineOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
