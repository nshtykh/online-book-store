package com.example.onlinebookstore.dto.cart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PutCartItemRequestDto {
    @Positive
    private int quantity;
}
