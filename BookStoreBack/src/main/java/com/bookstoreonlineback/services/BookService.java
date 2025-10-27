package com.bookstoreonlineback.services;

import com.bookstoreonlineback.DTO.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookDTO> searchBooks(String name, Double minPrice, Double maxPrice, Pageable pageable);
}
