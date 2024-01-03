package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join fetch b.categories c where c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);

    @Query("select b from Book b join fetch b.categories where b.id = :id")
    Optional<Book> findById(Long id);

    @Query("select b from Book b join fetch b.categories")
    List<Book> getAll(Pageable pageable);
}
