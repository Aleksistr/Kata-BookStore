package com.bookstoreonlineback.entities;

import com.bookstoreonlineback.enums.CartStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    CartStatus status = CartStatus.INITIALIZED;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<CartBook> items;

    public void addItem(CartBook item) {
        this.items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartBook item) {
        this.items.remove(item);
        item.setCart(null);
    }

    public double total() {
        return items.stream().mapToDouble( i -> i.getBook().getPrice() * i.getQuantity()).sum();
    }

}
