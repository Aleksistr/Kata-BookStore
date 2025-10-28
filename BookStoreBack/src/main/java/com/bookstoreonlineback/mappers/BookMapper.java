package com.bookstoreonlineback.mappers;

import com.bookstoreonlineback.DTO.BookDTO;
import com.bookstoreonlineback.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "author.id", target="author_id")
    BookDTO toDto (Book book);

}
