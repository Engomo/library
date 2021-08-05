package com.library.library.book;

import com.library.library.costumer.Costumer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void deleteBookById(long id) {
        repository.deleteById(id);
    }

//    @Transactional
//    public BookDto rentBook(long id, UpdateBookCommand command) {
//        Book book = repository.findById(id)
//                .orElseThrow(()-> new IllegalArgumentException("Book with id :" + id + "not found"));
//        book.setAvailability(BookAvailability.NOT_AVAILABLE);
//        book.setCostumer();
//    }


}
