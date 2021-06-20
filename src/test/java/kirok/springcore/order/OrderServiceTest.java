package kirok.springcore.order;

import static org.assertj.core.api.Assertions.assertThat;

import kirok.springcore.AppConfig;
import kirok.springcore.member.Grade;
import kirok.springcore.member.Member;
import kirok.springcore.member.MemberService;
import kirok.springcore.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = null;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

//    @Test
//    void fieldInjectionTest() {
//        OrderServiceImpl orderServiceTest = new OrderServiceImpl();
//        orderServiceTest.createOrder(1L, "name", 10000);
//    }


}
