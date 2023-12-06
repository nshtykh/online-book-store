package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    public Book save(Book book);

    public Optional<Book> findById(Long id);

    public List<Book> findAll();
}
