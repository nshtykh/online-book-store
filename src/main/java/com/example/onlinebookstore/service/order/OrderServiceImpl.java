package com.example.onlinebookstore.service.order;

import com.example.onlinebookstore.dto.order.OrderItemResponseDto;
import com.example.onlinebookstore.dto.order.OrderResponseDto;
import com.example.onlinebookstore.dto.order.PatchOrderRequestDto;
import com.example.onlinebookstore.dto.order.PostOrderRequestDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.OrderItemMapper;
import com.example.onlinebookstore.mapper.OrderMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.CartItem;
import com.example.onlinebookstore.model.Order;
import com.example.onlinebookstore.model.OrderItem;
import com.example.onlinebookstore.model.ShoppingCart;
import com.example.onlinebookstore.model.User;
import com.example.onlinebookstore.repository.CartItemRepository;
import com.example.onlinebookstore.repository.OrderItemRepository;
import com.example.onlinebookstore.repository.OrderRepository;
import com.example.onlinebookstore.repository.ShoppingCartRepository;
import com.example.onlinebookstore.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

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
    @Transactional
    public OrderResponseDto updateStatus(Long id, PatchOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find order with id " + id));
        order.setStatus(Order.Status.valueOf(requestDto.getStatus()));
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto placeOrder(Long userId, PostOrderRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find user with id " + userId));

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find shopping cart with id " + userId));

        BigDecimal total = countTotal(shoppingCart);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
        order.setTotal(total);
        order.setShippingAddress(requestDto.getShippingAddress());
        orderRepository.save(order);

        Set<OrderItem> orderItems = createOrderItems(shoppingCart, order);
        orderItemRepository.saveAll(orderItems);

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        return orderMapper.toDto(order);
    }

    private BigDecimal countTotal(ShoppingCart shoppingCart) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : shoppingCart.getCartItems()) {
            BigDecimal cartItemPrice = cartItem.getBook().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(cartItemPrice);
        }
        return total;
    }

    private Set<OrderItem> createOrderItems(ShoppingCart shoppingCart, Order order) {
        Set<OrderItem> orderItems = new HashSet<>();

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            Book book = cartItem.getBook();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(book.getPrice());
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
