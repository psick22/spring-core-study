package kirok.springcore.order;

import static org.assertj.core.api.Assertions.assertThat;

import kirok.springcore.discount.FixDiscountPolicy;
import kirok.springcore.member.Grade;
import kirok.springcore.member.Member;
import kirok.springcore.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImplTest {

    @Test
    void createOreder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,
            new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
