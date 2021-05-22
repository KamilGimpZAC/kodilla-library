package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.Title;
import com.crud.kodillalibrary.dto.TitleDto;
import com.crud.kodillalibrary.exception.TitleNotFound;
import com.crud.kodillalibrary.mapper.TitleMapper;
import com.crud.kodillalibrary.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/title")
public class TitleController {

    private final TitleService titleService;
    private final TitleMapper titleMapper;

    @Autowired
    public TitleController(TitleService titleService, TitleMapper titleMapper) {
        this.titleService = titleService;
        this.titleMapper = titleMapper;
    }

    @PostMapping(value = "createTitle")
    public void createTitle(@RequestBody TitleDto titleDto){
        titleService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @GetMapping(value = "getTitle")
    public TitleDto getTitle(@RequestParam Long id) throws TitleNotFound {
        return titleMapper.mapToTitleDto(titleService.getTitle(id).orElseThrow(TitleNotFound::new));
    }

    @GetMapping(value = "getTitles")
    public List<TitleDto> getTitles(){
        return titleMapper.mapToTitleDtoList(titleService.getAllTitles());
    }

    @PutMapping(value = "updateTitle")
    public TitleDto updateTitle(@RequestBody TitleDto titleDto){
        Long id = titleDto.getId();
        titleService.deleteTitle(id);
        titleService.saveTitle(titleMapper.mapToTitle(titleDto));
        return titleDto;
    }

    @DeleteMapping(value = "deleteTitle")
    public void deleteTitle(@RequestParam Long id){
        titleService.deleteTitle(id);
    }
}
