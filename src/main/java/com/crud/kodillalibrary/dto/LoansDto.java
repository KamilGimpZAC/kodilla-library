package com.crud.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoansDto {
    private long id;
    private LocalDate dateOfLoan;
    private LocalDate dateOfReturn;
    private CopyDto copyDto;
    private ReaderDto readerDto;
}
