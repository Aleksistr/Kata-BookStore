package com.bookstoreonlineback.DTO.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddRequestItemDTO {

    Long bookId;
    int quantity;
}
