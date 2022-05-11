package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); //save 시 저장할 메모리
    private static long sequence = 0L; //키 값 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); //id 셋팅하고 store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // NULL이어도 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //루프를 돌림, 찾으면 반환, 없으면 NULL포함해서 반환
                //파라미터로 넘어온 name과 멤버의 getName해서 받아온 이름과 같은지 확인
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store는 map이니까 List로 반환하려면 이렇게
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
