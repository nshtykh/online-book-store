package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.Book;
import java.util.List;

public interface BookService {
    public Book save(Book book);

    public List<Book> findAll();
}
