package com.shop.repository.custom;

import com.shop.domain.Member;

public interface MemberConfig {
    Member fingByMemberId(String memberId);
}
