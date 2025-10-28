package com.bookstoreonlineback.controller;

import com.bookstoreonlineback.DTO.BookDTO;
import com.bookstoreonlineback.mappers.BookMapper;
import com.bookstoreonlineback.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books/")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/search")
    public Page<BookDTO> searchBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Book controller - searchBook - begin");
        Page<BookDTO> books = bookService.searchBooks(name, minPrice, maxPrice, PageRequest.of(page, size)).map(bookMapper::toDto);;
        log.info("Book controller - searchBook - end ok");
        return books;
    }
}
