package com.library.library.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from books")
public class BookControllerIT {

    BookDto book;

    @Autowired
    TestRestTemplate template;

    @BeforeEach
            void setUp(){

    }

    @Test
    void testAddNewBook() {
        BookDto result = template.postForObject("/api/books", new CreateBookCommand("Férfi", "Csernus Imre"), BookDto.class);

        assertEquals("Férfi", result.getTitle());
    }

    @Test
    void testGetBooks() {

        template.postForObject("/api/books", new CreateBookCommand("Férfi", "Csernus Imre"), BookDto.class);
        template.postForObject("/api/books", new CreateBookCommand("Nő", "Csernus Imre"), BookDto.class);

        List<BookDto> excepted = template.exchange("/api/books", HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        }).getBody();

        assertEquals(2, excepted.size());
        assertThat(excepted)
                .extracting(BookDto::getTitle)
                .containsExactly("Férfi", "Nő");
    }
}