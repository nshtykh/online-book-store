package com.example.onlinebookstore.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequestDto {
    @NotNull
    private Long bookId;
    @NotNull
    private int quantity;
}
