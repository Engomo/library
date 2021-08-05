package com.library.library.book;

import com.library.library.costumer.Costumer;
import com.library.library.costumer.CostumerRepository;
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

    private CostumerRepository costumerRepository;

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

    @Transactional
    public BookDto rentBook(long id, UpdateBookCommand command) {
        Book book = repository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Book with id :" + id + "not found"));
        Costumer costumer = costumerRepository.findById(command.getCostumerId())
                .orElseThrow(()-> new IllegalArgumentException("Costumer with id: " + command.getCostumerId() + "not found"));
        book.setAvailability(BookAvailability.NOT_AVAILABLE);
        book.setCostumer(costumer);
        return modelMapper.map(book, BookDto.class);
    }


}
