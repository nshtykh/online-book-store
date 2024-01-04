package com.example.onlinebookstore.service.order;

import com.example.onlinebookstore.dto.order.OrderItemResponseDto;
import com.example.onlinebookstore.dto.order.OrderResponseDto;
import com.example.onlinebookstore.dto.order.PatchOrderRequestDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.OrderItemMapper;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderResponseDto> getAllOrders(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        return orderItems.stream().map(orderItemMapper::toDto).toList();
    }

    @Override
    public OrderItemResponseDto getOrderItem(Long orderId, Long itemId) {
        OrderItem orderItem = orderItemRepository.findByOrderIdAndId(orderId, itemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cant find order item with id " + itemId + " and order id " + orderId));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderResponseDto updateStatus(Long id, PatchOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order with id " + id));
        order.setStatus(Order.Status.valueOf(requestDto.getStatus()));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }
}
