package com.example.onlinebookstore.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private Set<OrderItemsResponseDto> orderItems;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private String status;
}
