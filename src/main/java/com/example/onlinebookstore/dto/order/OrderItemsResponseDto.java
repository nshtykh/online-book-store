package com.example.onlinebookstore.dto.order;

import lombok.Data;

@Data
public class OrderItemsResponseDto {
    private Long id;
    private Long bookId;
    private int quantity;
}
