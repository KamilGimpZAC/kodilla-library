package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {

    @Override
    List<Loans> findAll();
}
