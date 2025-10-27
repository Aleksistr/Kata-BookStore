package com.bookstoreonlineback.services.impl;

import com.bookstoreonlineback.DTO.BookDTO;
import com.bookstoreonlineback.entities.Book;
import com.bookstoreonlineback.mappers.BookMapper;
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
    private final BookMapper bookMapper;

    @Override
    public Page<BookDTO> searchBooks(String name, Double minPrice, Double maxPrice, Pageable pageable) {
        return bookRepository.searchBooks(name, minPrice, maxPrice, pageable).map(bookMapper::toBookDTO);
    }
}
