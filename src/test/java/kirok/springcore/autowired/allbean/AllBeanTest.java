package kirok.springcore.autowired.allbean;

import static org.assertj.core.api.Assertions.assertThat;

import kirok.springcore.AutoAppConfig;
import kirok.springcore.discount.DiscountService;
import kirok.springcore.member.Grade;
import kirok.springcore.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {


    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            AutoAppConfig.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);

        int discountPrice = discountService.discount(memberA, 10000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(memberA, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

}
