package com.example.onlinebookstore.service.cart;

import com.example.onlinebookstore.dto.cart.AddToCartRequestDto;
import com.example.onlinebookstore.dto.cart.PutCartItemRequestDto;
import com.example.onlinebookstore.dto.cart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto addToCart(AddToCartRequestDto requestDto, Long userId);

    ShoppingCartResponseDto getCartByUserId(Long userId);

    ShoppingCartResponseDto deleteByCartItemId(Long cartItemId, Long userId);

    ShoppingCartResponseDto updateByCartItemId(
            Long cartItemId,
            PutCartItemRequestDto requestDto,
            Long userId);
}
