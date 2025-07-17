package com.bvraju.aidept.StudentApplication.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
    @NonNull
    private String name;

    private String author;

}
