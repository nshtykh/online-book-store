package com.example.onlinebookstore;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.service.BookService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreApplication {
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book harryPotter1 = new Book();
            harryPotter1.setTitle("Harry Potter and the Philosopher's Stone");
            harryPotter1.setAuthor("Joanne Rowling");
            harryPotter1.setIsbn("789451212");
            harryPotter1.setPrice(BigDecimal.valueOf(250));

            bookService.save(harryPotter1);
            System.out.println(bookService.findAll());
        };
    }
}
