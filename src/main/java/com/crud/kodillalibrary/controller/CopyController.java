package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.Copy;
import com.crud.kodillalibrary.domain.CopyStatus;
import com.crud.kodillalibrary.domain.Title;
import com.crud.kodillalibrary.dto.CopyDto;
import com.crud.kodillalibrary.exception.CopyNotFound;
import com.crud.kodillalibrary.mapper.CopyMapper;
import com.crud.kodillalibrary.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/copy")
public class CopyController {

    private final CopyService copyService;
    private final CopyMapper copyMapper;

    @Autowired
    public CopyController(CopyService copyService, CopyMapper copyMapper) {
        this.copyService = copyService;
        this.copyMapper = copyMapper;
    }

    @PostMapping(value = "createCopy")
    public void createCopy(@RequestBody CopyDto copyDto){
        copyService.saveCopy(copyMapper.mapToCopy(copyDto));
    }

    @PutMapping(value = "changeStatus")
    public CopyStatus changeStatus(@RequestParam Long id, @RequestParam CopyStatus copyStatus) throws CopyNotFound {
        Copy copy = copyService.getCopy(id).orElseThrow(CopyNotFound::new);
        copy.setStatus(copyStatus);
        copyService.deleteCopy(id);
        copyService.saveCopy(copy);
        return copy.getStatus();
    }

    @GetMapping(value = "checkCopies")
    public int checkCopies(@RequestParam Long id){
        List<Copy> copies = copyService.getAllCopies();
        List<Copy> copiesThatWeWant = copies.stream()
                .filter(n -> n.getTitle().getId() == id)
                .collect(Collectors.toList());
        return copiesThatWeWant.size();
    }

    @GetMapping(value = "getCopy")
    public CopyDto getCopy(@RequestParam Long id) throws CopyNotFound{
        return copyMapper.mapToCopyDto(copyService.getCopy(id).orElseThrow(CopyNotFound::new));
    }

    @GetMapping(value = "getCopies")
    public List<CopyDto> getCopies(){
        return copyMapper.mapToCopyDtoList(copyService.getAllCopies());
    }

    @PutMapping(value = "updateCopy")
    public CopyDto updateCopy(@RequestBody CopyDto copyDto){
        Long id = copyDto.getId();
        copyService.deleteCopy(id);
        copyService.saveCopy(copyMapper.mapToCopy(copyDto));
        return copyDto;
    }

    @DeleteMapping(value = "deleteCopy")
    public void deleteCopy(@RequestParam Long id){
        copyService.deleteCopy(id);
    }
}
