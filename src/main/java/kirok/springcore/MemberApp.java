package kirok.springcore;

import kirok.springcore.member.Grade;
import kirok.springcore.member.Member;
import kirok.springcore.member.MemberService;
import kirok.springcore.member.MemberServiceImpl;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            AppConfig.class);

        MemberService memberService = applicationContext
            .getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}
