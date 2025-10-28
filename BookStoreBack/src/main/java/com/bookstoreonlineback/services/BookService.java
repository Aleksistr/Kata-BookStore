package com.bookstoreonlineback.services;

import com.bookstoreonlineback.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {


    Book getBookById(Long bookId);
    Page<Book> searchBooks(String name, Double minPrice, Double maxPrice, Pageable pageable);
}
