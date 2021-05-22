package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Copy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyRepository extends CrudRepository<Copy, Long> {

    @Override
    List<Copy> findAll();
}
