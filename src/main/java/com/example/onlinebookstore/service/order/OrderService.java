package com.example.onlinebookstore.service.order;

import com.example.onlinebookstore.dto.order.OrderResponseDto;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders(Long userId);
}
