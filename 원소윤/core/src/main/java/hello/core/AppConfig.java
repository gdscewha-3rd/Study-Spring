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
