package com.bookstoreonlineback.mappers;

import com.bookstoreonlineback.DTO.CartDTO;
import com.bookstoreonlineback.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "cart", target = "total", qualifiedByName = "getTotal")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "user.id", target = "userId")
    CartDTO toDto (Cart cart);

    @Named("getTotal")
    public static double getTotal(Cart cart) {
        return cart.total();
    }
}
