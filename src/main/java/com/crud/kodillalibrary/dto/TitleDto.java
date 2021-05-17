package com.crud.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private List<CopyDto> copyDtos;
}
