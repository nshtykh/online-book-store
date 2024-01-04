package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.order.OrderItemResponseDto;
import com.example.onlinebookstore.dto.order.OrderResponseDto;
import com.example.onlinebookstore.dto.order.PatchOrderRequestDto;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.order.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrders(user.getId());
    }

    @GetMapping("{orderId}/items")
    public List<OrderItemResponseDto> getOrderItemsById(@PathVariable Long orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }

    @GetMapping("{orderId}/items/{itemId}")
    public OrderItemResponseDto getOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long itemId) {
        return orderService.getOrderItem(orderId, itemId);
    }

    @PatchMapping("/{id}")
    public OrderResponseDto updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody PatchOrderRequestDto requestDto) {
        return orderService.updateStatus(id, requestDto);
    }
}
