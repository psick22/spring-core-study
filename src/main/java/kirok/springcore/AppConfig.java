package kirok.springcore;

import kirok.springcore.discount.DiscountPolicy;
import kirok.springcore.discount.FixDiscountPolicy;
import kirok.springcore.discount.RateDiscountPolicy;
import kirok.springcore.member.MemberRepository;
import kirok.springcore.member.MemberService;
import kirok.springcore.member.MemberServiceImpl;
import kirok.springcore.member.MemoryMemberRepository;
import kirok.springcore.order.OrderService;
import kirok.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }


}
