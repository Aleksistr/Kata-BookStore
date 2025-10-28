package com.bookstoreonlineback.services.impl;

import com.bookstoreonlineback.entities.Book;
import com.bookstoreonlineback.entities.Cart;
import com.bookstoreonlineback.entities.CartBook;
import com.bookstoreonlineback.entities.User;
import com.bookstoreonlineback.enums.CartStatus;
import com.bookstoreonlineback.exceptions.ResourceNotFoundException;
import com.bookstoreonlineback.reppositories.CartBookRepository;
import com.bookstoreonlineback.reppositories.CartRepository;
import com.bookstoreonlineback.reppositories.UserRepository;
import com.bookstoreonlineback.services.BookService;
import com.bookstoreonlineback.services.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartBookRepository cartBookRepository;
    private final BookService bookService;

    @Override
    public Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found: ".concat(String.valueOf(userId))));
                    Cart cart = Cart.builder().user(user).status(CartStatus.INITIALIZED).items(new ArrayList<>()).build();
                    return cartRepository.save(cart);
                });
    }

    @Override
    @Transactional
    public Cart addItem(Long userId, Long bookId, int quantity) {
        Book book = bookService.getBookById(bookId);
        Cart cart = getOrCreateCart(userId);

        // Check if we have to increase the item quantity or just add it to the list
        Optional<CartBook> existing = cart.getItems().stream()
                .filter(i -> i.getBook().getId().equals(bookId))
                .findFirst();

        if (existing.isPresent()) {
            CartBook item = existing.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartBookRepository.save(item);
        } else {
            CartBook item = new CartBook();
            item.setBook(book);
            item.setCart(cart);
            item.setQuantity(quantity);
            cart.addItem(item);
        }
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart removeItem(Long userId, Long bookId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: ".concat(String.valueOf(userId))));

        cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst()
                .ifPresent(item -> {
                    cart.removeItem(item);
                    cartBookRepository.delete(item);
                });

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart clear(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: ".concat(String.valueOf(userId))));

        cart.getItems().forEach(cartBookRepository::delete);
        cart.getItems().clear();
        return cart;
    }

    @Override
    public Cart orderCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user: ".concat(String.valueOf(userId))));

        cart.setStatus(CartStatus.ORDERED);
        return cartRepository.save(cart);
    }
}
