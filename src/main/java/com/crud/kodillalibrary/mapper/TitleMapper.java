package com.crud.kodillalibrary.mapper;

import com.crud.kodillalibrary.domain.Title;
import com.crud.kodillalibrary.dto.TitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleMapper {

    @Autowired
    private CopyMapper copyMapper;

    public Title mapToTitle(final TitleDto titleDto){
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationDate(),
                copyMapper.mapToCopyList(titleDto.getCopyDtos())
        );
    }

    public TitleDto mapToTitleDto(final Title title){
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationDate(),
                copyMapper.mapToCopyDtoList(title.getCopies())
        );
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titles){
        return titles.stream()
                .map(this::mapToTitleDto)
                .collect(Collectors.toList());
    }

    public List<Title> mapToTitleList(final List<TitleDto> titlesDto){
        return titlesDto.stream()
                .map(this::mapToTitle)
                .collect(Collectors.toList());
    }
}
