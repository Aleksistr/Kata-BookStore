package com.bookstoreonlineback.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartDTO {
    Long userId;
    Double total;
    List<CartBookDTO> items;
    String status;
}
