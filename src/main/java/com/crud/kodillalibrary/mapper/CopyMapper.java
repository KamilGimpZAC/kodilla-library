package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Copy;
import com.crud.kodillalibrary.dto.CopyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyMapper {

    @Autowired
    private LoansMapper loansMapper;

    @Autowired
    private TitleMapper titleMapper;

    public CopyDto mapToCopyDto(final Copy copy){
        return new CopyDto(
                copy.getId(),
                copy.getStatus(),
                titleMapper.mapToTitleDto(copy.getTitle()),
                loansMapper.mapToLoansDtoList(copy.getLoans())
        );
    }

    public Copy mapToCopy(final CopyDto copyDto){
        return new Copy(
                copyDto.getId(),
                copyDto.getCopyStatus(),
                titleMapper.mapToTitle(copyDto.getTitleDto()),
                loansMapper.mapToLoansList(copyDto.getLoansDtos())
        );
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copies){
        return copies.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }

    public List<Copy> mapToCopyList(final List<CopyDto> copiesDto){
        return copiesDto.stream()
                .map(this::mapToCopy)
                .collect(Collectors.toList());
    }
}
