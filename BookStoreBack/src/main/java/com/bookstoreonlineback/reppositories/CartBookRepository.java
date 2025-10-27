package com.bookstoreonlineback.reppositories;

import com.bookstoreonlineback.entities.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartBookRepository extends JpaRepository<CartBook, Long> {
}
