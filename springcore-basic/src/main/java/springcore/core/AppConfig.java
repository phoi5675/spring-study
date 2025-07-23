package springcore.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springcore.core.discount.DiscountPolicy;
import springcore.core.discount.FixDiscountPolicy;
import springcore.core.discount.RateDiscountPolicy;
import springcore.core.member.MemberService;
import springcore.core.member.MemberServiceImpl;
import springcore.core.member.MemoryMemberRepository;
import springcore.core.order.OrderService;
import springcore.core.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
