package com.example.onlinebookstore.service.order;

import com.example.onlinebookstore.dto.order.OrderResponseDto;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDto> getAllOrders(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream().map(orderMapper::toDto).toList();
    }
}
