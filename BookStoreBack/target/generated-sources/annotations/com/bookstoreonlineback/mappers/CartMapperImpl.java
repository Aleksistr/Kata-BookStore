package com.bookstoreonlineback.mappers;

import com.bookstoreonlineback.DTO.BookDTO;
import com.bookstoreonlineback.DTO.CartBookDTO;
import com.bookstoreonlineback.DTO.CartDTO;
import com.bookstoreonlineback.entities.Book;
import com.bookstoreonlineback.entities.Cart;
import com.bookstoreonlineback.entities.CartBook;
import com.bookstoreonlineback.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-28T11:56:30+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartDTO toDto(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setTotal( CartMapper.getTotal( cart ) );
        if ( cart.getStatus() != null ) {
            cartDTO.setStatus( cart.getStatus().name() );
        }
        cartDTO.setUserId( cartUserId( cart ) );
        cartDTO.setItems( cartBookListToCartBookDTOList( cart.getItems() ) );

        return cartDTO;
    }

    private Long cartUserId(Cart cart) {
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        return user.getId();
    }

    protected BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setName( book.getName() );
        bookDTO.setPrice( book.getPrice() );

        return bookDTO;
    }

    protected CartBookDTO cartBookToCartBookDTO(CartBook cartBook) {
        if ( cartBook == null ) {
            return null;
        }

        CartBookDTO cartBookDTO = new CartBookDTO();

        cartBookDTO.setBook( bookToBookDTO( cartBook.getBook() ) );
        cartBookDTO.setQuantity( cartBook.getQuantity() );

        return cartBookDTO;
    }

    protected List<CartBookDTO> cartBookListToCartBookDTOList(List<CartBook> list) {
        if ( list == null ) {
            return null;
        }

        List<CartBookDTO> list1 = new ArrayList<CartBookDTO>( list.size() );
        for ( CartBook cartBook : list ) {
            list1.add( cartBookToCartBookDTO( cartBook ) );
        }

        return list1;
    }
}
