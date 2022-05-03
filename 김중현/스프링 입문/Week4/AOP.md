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
> 공통 관심 사항과 핵심 관심 사항 분리

<img width="575" alt="스크린샷 2022-05-03 오후 5 45 03" src="https://user-images.githubusercontent.com/80838501/166426319-645e2acc-9fda-42f2-b83f-b0b7472ff111.png">

→ `TimeTraceAop`라는 클래스를 새로 만들어 시간 측정 로직을 작성한다. 그리고 원하는 곳에 공통 관심 사항을 적용
<br>
<br>

**TimeTraceAop**
```java
@Component 
@Aspect
public class TimeTraceAop { //시간 측정 로직 작성
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
```
**→ AOP를 통한 해결**
- 핵심 관심 사항(회원 가입, 회원 조회)과 공통 관심 사항(시간 측정)을 분리
- 핵심 관심 사항을 깔끔하게 유지할 수 있고, 변경이 필요할 시 공통 관심 사항 로직만 변경
- 시간 측정 로직을 별도의 공통 로직으로 생성
- 원하는 적용 대상을 쉽게 선택 가능
<br>
<br>

**AOP 동작 방식**
- AOP 적용 전
<img width="575" alt="스크린샷 2022-05-03 오후 5 55 51" src="https://user-images.githubusercontent.com/80838501/166427693-1c589235-fcd9-4883-a1f5-3eac696cd704.png">

→ 의존관계에 따라 호출
<br>
<br>

- AOP 적용 후 
<img width="575" alt="스크린샷 2022-05-03 오후 5 56 20" src="https://user-images.githubusercontent.com/80838501/166427708-e30f6f32-9785-4cc3-afbd-c032d195a7f5.png">

→ 가짜 스프링 빈(프록시)이 생성되고, memberController는 가짜 memberService(프록시)를, memberService는 <br>
  가짜 memberRepository(프록시)를 호출
