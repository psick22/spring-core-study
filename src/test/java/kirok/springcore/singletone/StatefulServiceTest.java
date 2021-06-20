package kirok.springcore.singletone;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    @DisplayName("statful 싱글톤 서비스의 문제점")
    void statefulService() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            TestConfig.class);

        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10000원 주문
        bean1.order("userA", 10000);

        // ThreadA : A 사용자가 20000원 주문
        bean2.order("userB", 20000);

        // ThreadA : A 사용자가 주문 금액 조회
        int price = bean1.getPrice();
        System.out.println("price = " + price);

        assertThat(price).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }

    }

}
