package com.example.onlinebookstore.service.category;

import com.example.onlinebookstore.dto.category.CategoryRequestDto;
import com.example.onlinebookstore.dto.category.CategoryResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryResponseDto> getAll(Pageable pageable);

    CategoryResponseDto save(CategoryRequestDto requestDto);

    CategoryResponseDto findById(Long id);

    CategoryResponseDto update(Long id, CategoryRequestDto requestDto);

    void deleteById(Long id);
}
