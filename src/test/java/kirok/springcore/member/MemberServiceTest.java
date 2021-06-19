package kirok.springcore.member;

import static org.assertj.core.api.Assertions.assertThat;

import kirok.springcore.AppConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void before() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);
        assertThat(findMember).isEqualTo(memberA);
    }

}
