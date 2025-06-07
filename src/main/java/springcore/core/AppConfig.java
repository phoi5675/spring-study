package springcore.core;

import springcore.core.discount.FixDiscountPolicy;
import springcore.core.member.MemberService;
import springcore.core.member.MemberServiceImpl;
import springcore.core.member.MemoryMemberRepository;
import springcore.core.order.OrderService;
import springcore.core.order.OrderServiceImpl;

public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
