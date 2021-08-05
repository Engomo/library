package com.library.library.book;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private ModelMapper modelMapper;

    private BookRepository repository;

    public BookDto addBook(CreateBookCommand command) {
    Book book = new Book(command.getTitle(), command.getAuthor());
    repository.save(book);
    return modelMapper.map(book, BookDto.class);
    }

    public List<BookDto> getBooks() {
        return repository.findAll().stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .collect(Collectors.toList());
    }
}
