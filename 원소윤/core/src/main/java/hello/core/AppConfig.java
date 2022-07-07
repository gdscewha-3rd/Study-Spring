package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //애플리케이션의 설정 정보
public class AppConfig {

    // @Bean -> memberService -> new MemoryMemberRepository()
    // @Bean -> orderService -> new MemoryMemberRepository() => 싱글톤이 깨지게 되는 것 아닌가?
    // 아니다! 스프링이 빈을 등록하는 과정에서 바이트코드 조작라이브러리를 사용해서 다른 클래스를 만들고, 이를 등록한 것이다..
    // @Configuration 빼면 싱글톤이 깨진다

    @Bean //스프링 컨테이너가 @Bean 붙은 것을 하나씩 보고 호출 함
    // memberService라는 메소드 이름을 키로 가지고
    // return 한 반환 값을 빈 객체로 등록을 함
    // 이런 정보가 담겨있는 것을 스프링 빈이라고 함
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository()); //생성자 주입
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
            return new OrderServiceImpl(memberRepository(), discountPolicy());
     }

    @Bean
     public DiscountPolicy discountPolicy(){ //메소드명에서 역할이 보임
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
     }
}
