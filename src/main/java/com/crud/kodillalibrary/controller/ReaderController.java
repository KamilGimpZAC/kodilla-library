package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.dto.ReaderDto;
import com.crud.kodillalibrary.exception.ReaderNotFound;
import com.crud.kodillalibrary.mapper.ReaderMapper;
import com.crud.kodillalibrary.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/reader")
public class ReaderController {
    private final ReaderMapper readerMapper;
    private final ReaderService readerService;

    @Autowired
    public ReaderController(ReaderMapper readerMapper, ReaderService readerService) {
        this.readerMapper = readerMapper;
        this.readerService = readerService;
    }

    @PostMapping(value = "createReader")
    public void createReader(@RequestBody ReaderDto readerDto){
        readerService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @GetMapping(value = "getReader")
    public ReaderDto getReader(@RequestParam Long id) throws ReaderNotFound{
        return readerMapper.mapToReaderDto(readerService.getReader(id).orElseThrow(ReaderNotFound::new));
    }

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders(){
        return readerMapper.mapToReaderDtoList(readerService.getAllReaders());
    }

    @PutMapping(value = "updateReader")
    public ReaderDto updateReader(@RequestBody ReaderDto readerDto){
        Long id = readerDto.getId();
        readerService.deleteReader(id);
        readerService.saveReader(readerMapper.mapToReader(readerDto));
        return readerDto;
    }

    @DeleteMapping(value = "deleteReader")
    public void deleteReader(@RequestParam Long id){
        readerService.deleteReader(id);
    }

}
