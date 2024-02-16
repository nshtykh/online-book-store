package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.repository.book.BookRepository;
import java.math.BigDecimal;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Get all books with categories")
    @Sql(scripts = "classpath:database/books/"
            + "add-book-and-category-to-books-and-categories-tables.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/books/"
            + "delete-book-and-category-from-books-and-categories-tables.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findById_BookWithCategory_ReturnsBook() {
        Set<Category> categories = Set.of(new Category().setId(1L).setName("fantasy"));
        Book expectedBook = new Book().setId(1L)
                .setTitle("Harry Potter")
                .setAuthor("J. K. Rowling")
                .setIsbn("9781111111111")
                .setPrice(new BigDecimal("100.00"))
                .setCategories(categories);

        Book actualBook = bookRepository.findById(1L).get();

        Assertions.assertEquals(expectedBook, actualBook);
    }
}
