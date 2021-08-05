package com.library.library.costumer;

import com.library.library.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "costumers")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "costumer")
    private List<Book> rentedBooks;

    public void rentBook(Book book) {
        if (rentedBooks==null) {
            rentedBooks = new ArrayList<>();
        }
        rentedBooks.add(book);
        book.setCostumer(this);
    }

}
