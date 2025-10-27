package com.bookstoreonlineback.mappers;

import com.bookstoreonlineback.DTO.BookDTO;
import com.bookstoreonlineback.entities.Author;
import com.bookstoreonlineback.entities.Book;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-27T23:40:24+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO toBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setAuthor_id( bookAuthorId( book ) );
        bookDTO.setName( book.getName() );
        bookDTO.setPrice( book.getPrice() );

        return bookDTO;
    }

    private Long bookAuthorId(Book book) {
        Author author = book.getAuthor();
        if ( author == null ) {
            return null;
        }
        return author.getId();
    }
}
