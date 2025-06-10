package com.library.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    long id;
    String title;
    String author;
}
