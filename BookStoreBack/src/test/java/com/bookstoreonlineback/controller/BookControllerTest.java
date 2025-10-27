package com.bookstoreonlineback.controller;

import com.bookstoreonlineback.entities.Author;
import com.bookstoreonlineback.entities.Book;
import com.bookstoreonlineback.reppositories.AuthorRepository;
import com.bookstoreonlineback.reppositories.BookRepository;
import com.bookstoreonlineback.services.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Spy
    BookServiceImpl bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        Author author = new Author();
        author.setFirstname("Jean");
        author.setLastname("Dupont");
        authorRepository.save(author);

        bookRepository.save(Book.builder().name("Le feu sacré").price(19.99).author(author).build());
        bookRepository.save(Book.builder().name("Sous la glace").price(14.50).author(author).build());
        bookRepository.save(Book.builder().name("Feu de glace").price(24.90).author(author).build());
    }

    @Test
    void testSearchBooksByName() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("name", "feu")
                        .param("page", "0")
                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", containsStringIgnoringCase("feu")));
    }

    @Test
    void testSearchBooksByPriceRange() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("minPrice", "15")
                        .param("maxPrice", "22")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].price", everyItem(allOf(greaterThanOrEqualTo(15.0), lessThanOrEqualTo(22.0)))));
    }

    @Test
    void testSearchBooksPagination() throws Exception {
        mockMvc.perform(get("/api/books/search")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements", is(3)));
    }
}