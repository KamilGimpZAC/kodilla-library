package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.Copy;
import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopyService {

    private final CopyRepository copyRepository;

    public List<Copy> getAllCopies(){
        return copyRepository.findAll();
    }

    public Copy saveCopy(final Copy copy){
        return copyRepository.save(copy);
    }

    public Optional<Copy> getCopy(final Long id){
        return copyRepository.findById(id);
    }

    public void deleteCopy(final Long id){
        copyRepository.deleteById(id);
    }
}
