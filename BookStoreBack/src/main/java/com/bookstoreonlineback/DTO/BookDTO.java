package com.bookstoreonlineback.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDTO {

    Long id;
    String name;
    Double price;
    Long author_id;
}
