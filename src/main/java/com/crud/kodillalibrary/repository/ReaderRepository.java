package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    @Override
    List<Reader> findAll();
}
