package com.library.library.costumer;

import com.library.library.book.Book;
import com.library.library.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerDto {


    private Long id;


    private String name;


    private LocalDate dateOfBirth;


    private List<BookDto> rentedBooks;
}
