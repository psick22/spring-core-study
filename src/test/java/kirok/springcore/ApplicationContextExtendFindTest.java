package kirok.springcore;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;
import java.util.Map;
import kirok.springcore.discount.DiscountPolicy;
import kirok.springcore.discount.FixDiscountPolicy;
import kirok.springcore.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        TestConfig.class);

    @Test
    @DisplayName("부모타입으로 조회시 자식이 둘이상있으면 중복 오류")
    void findBeanByParentType() {

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
            () -> ac.getBean(DiscountPolicy.class));


    }

    @Test
    @DisplayName("부모타입으로 조회시 이름 지정")
    void findBeanByParentTypeAndName() {

        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

    }

    @Test
    @DisplayName("부모타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("s = " + s);
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회(Object)")
    void findAllBeanByParentType2() {
        Map<String, Object> beansOfType1 = ac.getBeansOfType(Object.class);
        for (String s : beansOfType1.keySet()) {
            System.out.println("s = " + s);
        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }


}
