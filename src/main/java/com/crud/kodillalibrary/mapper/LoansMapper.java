package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Loans;
import com.crud.kodillalibrary.dto.LoansDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoansMapper {

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private CopyMapper copyMapper;

    public LoansDto mapToLoansDto(final Loans loans){
        return new LoansDto(
                loans.getId(),
                loans.getDateOfLoan(),
                loans.getDateOfReturn(),
                copyMapper.mapToCopyDto(loans.getCopy()),
                readerMapper.mapToReaderDto(loans.getReader())
        );
    }

   public Loans mapToLoans(final LoansDto loansDto){
        return new Loans(
                loansDto.getId(),
                loansDto.getDateOfLoan(),
                loansDto.getDateOfReturn(),
                copyMapper.mapToCopy(loansDto.getCopyDto()),
                readerMapper.mapToReader(loansDto.getReaderDto())
        );
   }


    public List<LoansDto> mapToLoansDtoList(final List<Loans> loans){
        return loans.stream()
                .map(this::mapToLoansDto)
                .collect(Collectors.toList());
    }

    public List<Loans> mapToLoansList(final List<LoansDto> loans){
        return loans.stream()
                .map(this::mapToLoans)
                .collect(Collectors.toList());
    }
}
