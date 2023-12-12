package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.BookDto;
import com.example.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    public BookDto save(CreateBookRequestDto requestDto);

    public List<BookDto> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);
}
