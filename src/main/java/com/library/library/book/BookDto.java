package com.library.library.book;

import com.library.library.costumer.CostumerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private boolean removable = true;

    private CostumerDto costumer;
}
