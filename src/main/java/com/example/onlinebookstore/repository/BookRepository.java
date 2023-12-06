package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import java.util.List;

public interface BookRepository {
    public Book save(Book book);

    public List<Book> findAll();
}
