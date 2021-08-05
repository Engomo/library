package com.library.library.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookCommand {

    @NotNull
    private Long costumerId;

    private BookAvailability availability;
}
