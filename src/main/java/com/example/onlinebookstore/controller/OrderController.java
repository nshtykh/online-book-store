package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.order.OrderResponseDto;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.service.order.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
}
