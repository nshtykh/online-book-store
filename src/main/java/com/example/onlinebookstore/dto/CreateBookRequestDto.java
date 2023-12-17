package com.example.onlinebookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    private static final String EXCEPTION_MSG = "must start with 978 or 979 and contain 13 digits!";
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    @Pattern(regexp = "978\\d{10}|979\\d{10}", message = EXCEPTION_MSG)
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
