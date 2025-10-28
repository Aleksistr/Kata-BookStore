package com.bookstoreonlineback.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartBookDTO {

    BookDTO book;
    Integer quantity;

}
