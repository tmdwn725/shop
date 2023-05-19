package com.shop.member;

import com.shop.domain.Member;
import com.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
@SpringBootTest
@WebAppConfiguration
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void testMember(){
        Member member = new Member();
        member.craeteMember("sjmoon", "문승주", passwordEncoder.encode("1234"));
        memberRepository.save(member);
    }

}
