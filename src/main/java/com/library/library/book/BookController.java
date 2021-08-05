package com.library.library.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostPersist;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
@Tag(name = "Management of books")
public class BookController {

    private BookService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto addBook(@RequestBody CreateBookCommand command) {
        return service.addBook(command);
    }

    @GetMapping
    public List<BookDto> getBooks () {
        return service.getBooks();
    }
}
