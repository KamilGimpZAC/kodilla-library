package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.Title;
import com.crud.kodillalibrary.repository.TitleRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    public List<Title> getAllTitles(){
        return titleRepository.findAll();
    }

    public Title saveTitle(final Title title){
        return titleRepository.save(title);
    }

    public Optional<Title> getTitle(final Long id){
        return titleRepository.findById(id);
    }

    public void deleteTitle(final Long id){
        titleRepository.findById(id);
    }
}
