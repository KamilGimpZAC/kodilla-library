package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.dto.ReaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderMapper {

    @Autowired
    private LoansMapper loansMapper;

    public Reader mapToReader(final ReaderDto readerDto){
        return new Reader(
                readerDto.getId(),
                readerDto.getName(),
                readerDto.getSurname(),
                readerDto.getAccountCreationDate(),
                loansMapper.mapToLoansList(readerDto.getLoansDtos())
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader){
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getAccountCreationDate(),
                loansMapper.mapToLoansDtoList(reader.getLoans())
        );
    }

    public List<Reader> mapToReaderList(final List<ReaderDto> readerDtos){
        return readerDtos.stream()
                .map(this::mapToReader)
                .collect(Collectors.toList());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers){
        return readers.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}
