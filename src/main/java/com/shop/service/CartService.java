package com.shop.service;

import com.shop.domain.Cart;
import com.shop.domain.Member;
import com.shop.domain.ProductStock;
import com.shop.dto.CartDTO;
import com.shop.repository.CartRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductStockRepository productStockRepository;
    public void addCart(CartDTO cartDTO, String memberId){
        Cart cart = new Cart();
        Member member = memberRepository.fingByMemberId(memberId);
        Optional<ProductStock> productStock = productStockRepository.findById(cartDTO.getProductStockSeq());
        cart.createCart(member, productStock.get(), cartDTO.getQuantity());
        cartRepository.save(cart);
    }
}
