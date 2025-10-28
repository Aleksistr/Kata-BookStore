package com.bookstoreonlineback.services;

import com.bookstoreonlineback.entities.Cart;

public interface CartService {

    Cart getOrCreateCart(Long userId);
    Cart addItem(Long userId, Long bookId, int qty);
    Cart removeItem(Long userId, Long bookId);
    Cart clear (Long userId);
    Cart orderCart(Long userId);
}
