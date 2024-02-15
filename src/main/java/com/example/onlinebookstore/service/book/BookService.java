package com.example.onlinebookstore.service.book;

import com.example.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.book.BookResponseDto;
import com.example.onlinebookstore.dto.book.BookSearchParameters;
import com.example.onlinebookstore.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(CreateBookRequestDto requestDto);

    List<BookResponseDto> findAll(Pageable pageable);

    BookResponseDto findById(Long id);

    void deleteById(Long id);

    BookResponseDto update(Long id, CreateBookRequestDto requestDto);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long categoryId);

    List<BookResponseDto> search(BookSearchParameters parameters);
}
