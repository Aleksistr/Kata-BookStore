package com.bookstoreonlineback;

import com.bookstoreonlineback.BookStoreOnlineBack;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookStoreOnlieBackTests {

    @Test
    void contextLoad() {
        BookStoreOnlineBack.main(new String[]{});
    }
}
