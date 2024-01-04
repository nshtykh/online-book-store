package com.example.onlinebookstore.service.order;

import com.example.onlinebookstore.dto.order.OrderItemResponseDto;
import com.example.onlinebookstore.dto.order.OrderResponseDto;
import com.example.onlinebookstore.dto.order.PatchOrderRequestDto;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders(Long userId);

    List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId);

    OrderItemResponseDto getOrderItem(Long orderId, Long itemId);

    OrderResponseDto updateStatus(Long id, PatchOrderRequestDto requestDto);
}
