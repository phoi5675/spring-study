package springcore.core.discount;

import org.springframework.stereotype.Component;
import springcore.core.member.Grade;
import springcore.core.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
