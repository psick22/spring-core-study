package kirok.springcore.singletone;


import static org.assertj.core.api.Assertions.assertThat;

import kirok.springcore.AppConfig;
import kirok.springcore.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTest {

    @Test
    @DisplayName("순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

    }


    @Test
    @DisplayName("싱글톤 객체 사용")
    void singletoneService() {
        SingleToneService instance1 = SingleToneService.getInstance();
        SingleToneService instance2 = SingleToneService.getInstance();
        assertThat(instance1).isSameAs(instance2);

    }

    @Test
    @DisplayName("스프링 컨테이너/싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(
            AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);

    }


}
