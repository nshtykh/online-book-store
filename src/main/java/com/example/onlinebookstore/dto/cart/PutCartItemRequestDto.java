package com.example.onlinebookstore.dto.cart;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PutCartItemRequestDto {
    @NotNull
    @Positive
    private int quantity;
}
