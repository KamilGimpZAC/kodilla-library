package com.crud.kodillalibrary.dto;

import com.crud.kodillalibrary.domain.CopyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CopyDto {
    private long id;
    private CopyStatus copyStatus;
    private TitleDto titleDto;
    private List<LoansDto> loansDtos;
}
