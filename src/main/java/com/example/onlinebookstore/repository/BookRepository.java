package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join fetch b.categories c where c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);
}
