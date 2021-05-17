package com.crud.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReaderDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate accountCreationDate;
    private List<LoansDto> loansDtos;
}
