package com.shop.repository.custom;

import com.shop.domain.Cart;
import com.shop.domain.Member;

import java.util.List;

public interface CartConfig {
    List<Cart> myCartList(Member member);
}
