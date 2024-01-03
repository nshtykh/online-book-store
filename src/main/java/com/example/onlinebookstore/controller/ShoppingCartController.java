package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.cart.AddToCartRequestDto;
import com.example.onlinebookstore.dto.cart.PutCartItemRequestDto;
import com.example.onlinebookstore.dto.cart.ShoppingCartResponseDto;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping
    @Operation(summary = "Add book to shopping cart", description = "Add book to shopping cart")
    public ShoppingCartResponseDto addToCart(
            @RequestBody @Valid AddToCartRequestDto requestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();;

        return shoppingCartService.addToCart(requestDto, user.getId());
    }

    @GetMapping
    @Operation(summary = "Get shopping cart", description = "Get shopping cart with books")
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getCartByUserId(user.getId());
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Delete book from shopping cart", description = "Delete book from shopping cart")
    public ShoppingCartResponseDto removeFromCart(Authentication authentication,
                                                  @PathVariable Long cartItemId) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.deleteByCartItemId(cartItemId, user.getId());
    }

    @PutMapping("/cart-items/{cartItemId}")
    @Operation(summary = "Update book quantity in shopping cart", description = "Update book quantity in shopping cart")
    public ShoppingCartResponseDto updateCartItem(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @RequestBody @Valid PutCartItemRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateByCartItemId(cartItemId, requestDto, user.getId());
    }

}
