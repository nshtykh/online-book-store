package com.example.onlinebookstore.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatchOrderRequestDto {
    @NotNull
    private String status;
}
