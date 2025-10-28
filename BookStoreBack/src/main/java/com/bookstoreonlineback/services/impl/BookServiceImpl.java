package com.bookstoreonlineback.services.impl;

import com.bookstoreonlineback.entities.Book;
import com.bookstoreonlineback.exceptions.ResourceNotFoundException;
import com.bookstoreonlineback.reppositories.BookRepository;
import com.bookstoreonlineback.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found".concat(String.valueOf(bookId))));
    }

    @Override
    public Page<Book> searchBooks(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        return bookRepository.searchBooks(name, minPrice, maxPrice, pageable);
    }
}
