package com.shop.service;

import com.shop.common.ModelMapperUtil;
import com.shop.domain.Member;
import com.shop.dto.MemberDTO;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.fingByMemberId(username);
        return User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }

    public MemberDTO selectMemberById(String id) {
        MemberDTO dto = ModelMapperUtil.map(memberRepository.fingByMemberId(id), MemberDTO.class);
        return dto;
    }
}
