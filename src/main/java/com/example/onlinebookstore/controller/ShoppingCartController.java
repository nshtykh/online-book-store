package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.cart.AddToCartRequestDto;
import com.example.onlinebookstore.dto.cart.ShoppingCartResponseDto;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping
    public ShoppingCartResponseDto addToCart(
            @RequestBody @Valid AddToCartRequestDto requestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();;

        return shoppingCartService.addToCart(requestDto, user.getId());
    }

    @GetMapping
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getCartByUserId(user.getId());
    }

}
