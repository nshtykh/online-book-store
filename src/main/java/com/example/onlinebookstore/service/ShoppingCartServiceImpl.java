package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.cart.AddToCartRequestDto;
import com.example.onlinebookstore.dto.cart.PutCartItemRequestDto;
import com.example.onlinebookstore.dto.cart.ShoppingCartResponseDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.ShoppingCartMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.repository.CartItemRepository;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String EXP_MSG_CAN_NOT_FIND_CART = "Can't find cart by user id ";
    private static final String EXP_MSG_CAN_NOT_FIND_BOOK = "Can't find book with id ";
    private static final String EXP_MSG_CAN_NOT_FIND_USER = "Can't find cart by user id ";
    private static final String EXP_MSG_CAN_NOT_FIND_ITEM = "Can't find cart item with id ";
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartResponseDto addToCart(AddToCartRequestDto requestDto, Long userId) {
        Long bookId = requestDto.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        EXP_MSG_CAN_NOT_FIND_BOOK + bookId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        EXP_MSG_CAN_NOT_FIND_USER + userId));

        ShoppingCart shoppingCartFromDb = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCartFromDb);
        cartItemRepository.save(cartItem);
        shoppingCartFromDb.getCartItems().add(cartItem);

        return shoppingCartMapper.toDto(shoppingCartFromDb);
    }

    @Override
    public ShoppingCartResponseDto getCartByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        EXP_MSG_CAN_NOT_FIND_CART + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto deleteByCartItemId(Long cartItemId, Long userId) {
        cartItemRepository.deleteById(cartItemId);
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserId(userId).orElseThrow(() -> new EntityNotFoundException(
                        EXP_MSG_CAN_NOT_FIND_CART + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartResponseDto updateByCartItemId(
            Long cartItemId,
            PutCartItemRequestDto requestDto,
            Long userId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        EXP_MSG_CAN_NOT_FIND_ITEM + cartItemId));
        cartItem.setQuantity(requestDto.getQuantity());
        ShoppingCart shoppingCart = shoppingCartRepository
                .findByUserId(userId).orElseThrow(() -> new EntityNotFoundException(
                        EXP_MSG_CAN_NOT_FIND_CART + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }
}
