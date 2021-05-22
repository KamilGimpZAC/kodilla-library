package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }

    public Reader saveReader(final Reader reader){
        return readerRepository.save(reader);
    }

    public Optional<Reader> getReader(final Long id){
        return readerRepository.findById(id);
    }

    public void deleteReader(final Long id){
        readerRepository.deleteById(id);
    }
}
