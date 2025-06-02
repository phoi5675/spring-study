package phoi.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phoi.hello_spring.repository.MemberRepository;
import phoi.hello_spring.repository.MemoryMemberRepository;
import phoi.hello_spring.service.MemberService;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
