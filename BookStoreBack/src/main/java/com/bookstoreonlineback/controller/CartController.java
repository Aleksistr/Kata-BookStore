package com.bookstoreonlineback.controller;

import com.bookstoreonlineback.DTO.CartDTO;
import com.bookstoreonlineback.DTO.requests.AddRequestItemDTO;
import com.bookstoreonlineback.entities.Cart;
import com.bookstoreonlineback.mappers.CartMapper;
import com.bookstoreonlineback.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;


    @GetMapping("/{userId}")
    public CartDTO getCart(@PathVariable Long userId) {
        Cart cart = cartService.getOrCreateCart(userId);
        return cartMapper.toDto(cart);
    }

    @PostMapping(value = "/{userId}/items", consumes = "application/json")
    public CartDTO addItem(
            @PathVariable Long userId,
            @RequestBody AddRequestItemDTO request
    ) {
        Cart cart = cartService.addItem(userId, request.getBookId(), request.getQuantity());
        return cartMapper.toDto(cart);
    }

    @DeleteMapping("/{userId}/book/{bookId}")
    public CartDTO removeItem(
            @PathVariable Long userId,
            @PathVariable Long bookId
    ) {
        Cart cart = cartService.removeItem(userId, bookId);
        return cartMapper.toDto(cart);
    }

    @DeleteMapping("/{userId}")
    public CartDTO clearCart (
            @PathVariable Long userId
    ) {
        Cart cart = cartService.clear(userId);
        return cartMapper.toDto(cart);
    }

    @PutMapping("/{userId}/order")
    public CartDTO orderCart (
            @PathVariable Long userId
    ) {
        Cart cart = cartService.orderCart(userId);
        return cartMapper.toDto(cart);
    }
}
