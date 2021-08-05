package com.library.library.book;

import com.library.library.costumer.Costumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private boolean removable = true;

    @ManyToOne(cascade = CascadeType.ALL)
    private Costumer costumer;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
