## Section 7. AOP
### AOP가 필요한 상황
> AOP: Aspect Oriented Programming

- 만약 모든 메소드의 호출 시간을 측정하고 싶다면?<br>
  수많은 메소드에 직접 하나하나 호출 시간을 측정하는 코드를 넣는 것이 과연 효율적일까? <br>
<br>

**Ex)** 회원 가입 시간과 회원 조회 시간을 측정하고 싶은 경우, 메소드마다 시간 측정 로직을 추가한다면
<img width="577" alt="스크린샷 2022-05-03 오후 5 19 45" src="https://user-images.githubusercontent.com/80838501/166423420-ed02b994-1f7e-42ef-b104-9fe974e731d9.png">
<br>

**MemberService**
```java
package hello.hellospring.service;
@Transactional
public class MemberService {

    /**
    * 회원가입
    */
    public Long join(Member member) {
        long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); //중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis(); 
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        } 
    }

    /**
    *전체 회원 조회
    */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
         try {
            return memberRepository.findAll();
         } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
         } 
    }
}
```
**→ 문제점**
- 회원 가입과 회원 조회에서 시간 측정하는 기능은 핵심 관심 사항이 아니다.
  - 시간 측정 로직은 공통 관심 사항
- 시간 측정 로직과 핵심 비즈니스 로직이 섞여 유지보수가 어렵다.
- 시간 측정 로직을 별도의 공통 로직으로 만들기는 매우 어렵다.
- 시간 측정 로직에 변경 사항이 있을 경우, 모든 로직을 찾아 직접 변경해야 한다.
<br>
<br>
<br>

### AOP 적용
