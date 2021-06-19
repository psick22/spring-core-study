package kirok.springcore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kirok.springcore.member.MemberService;
import kirok.springcore.member.MemberServiceImpl;
import kirok.springcore.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);

        }

    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("빈 조회")
    void findBean() {
        Object memberRepository = ac.getBean("memberRepository");
        assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);

        MemberService bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberServiceImpl.class);

        MemberService bean2 = ac.getBean(MemberServiceImpl.class);

        assertThat(bean2).isInstanceOf(MemberServiceImpl.class);
        assertThat(bean2).isEqualTo(bean);
    }

    @Test

    @DisplayName("빈 조회 실패")
    void findBeanFail() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("dfajlksdjflk"));
    }

}
