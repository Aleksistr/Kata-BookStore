package com.bookstoreonlineback.entities;

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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    Double price;

    @OneToMany(mappedBy = "book")
    List<CartBook> cartBooks;

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    Author author;
}
