package com.bookstoreonlineback.controller;

import com.bookstoreonlineback.DTO.requests.AddRequestItemDTO;
import com.bookstoreonlineback.entities.Author;
import com.bookstoreonlineback.entities.Book;
import com.bookstoreonlineback.entities.User;
import com.bookstoreonlineback.reppositories.AuthorRepository;
import com.bookstoreonlineback.reppositories.BookRepository;
import com.bookstoreonlineback.reppositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        userRepository.deleteAll();

        Author author = new Author();
        author.setFirstname("Jean");
        author.setLastname("Dupont");
        author = authorRepository.save(author);

        userRepository.save(User.builder().username("test").build());

        bookRepository.save(Book.builder().name("Le feu sacré").price(19.99).author(author).build());
        bookRepository.save(Book.builder().name("Sous la glace").price(14.50).author(author).build());
        bookRepository.save(Book.builder().name("Feu de glace").price(24.90).author(author).build());
    }

    @Test
    void shouldAddBookToCart() throws Exception {
        Book book = bookRepository.findAll().get(0);
        Long userId = userRepository.findAll().get(0).getId();

        AddRequestItemDTO req = new AddRequestItemDTO();
        req.setBookId(book.getId());
        req.setQuantity(2);

        mockMvc.perform(post("/api/carts/{userId}/items", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.items[0].bookId").value(book.getId()))
                .andExpect(jsonPath("$.items[0].quantity").value(2))
                .andExpect(jsonPath("$.total").value(book.getPrice() * 2));
    }

    @Test
    void shouldRemoveBookFromCart() throws Exception {
        Long bookId = bookRepository.findAll().get(0).getId();
        Long userId = userRepository.findAll().get(0).getId();

        // Add item first
        AddRequestItemDTO req = new AddRequestItemDTO();
        req.setBookId(bookId);
        req.setQuantity(1);

        mockMvc.perform(post("/api/carts/{userId}/items", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().is2xxSuccessful());

        // Then remove it
        mockMvc.perform(delete("/api/carts/{userId}/book/{bookId}", userId, bookId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.items.length()").value(0))
                .andExpect(jsonPath("$.total").value(0.0));
    }

    @Test
    void shouldCheckoutCartAndCreateOrder() throws Exception {
        Book book = bookRepository.findAll().get(0);
        Long userId = userRepository.findAll().get(0).getId();

        // Add item first
        AddRequestItemDTO req = new AddRequestItemDTO();
        req.setBookId(book.getId());
        req.setQuantity(1);

        mockMvc.perform(post("/api/carts/{userId}/items", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().is2xxSuccessful());

        // Checkout
        mockMvc.perform(put("/api/carts/{userId}/order", userId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.total").value(book.getPrice()));
    }
}
