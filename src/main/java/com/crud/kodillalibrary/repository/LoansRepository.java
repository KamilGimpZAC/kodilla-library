package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends CrudRepository<Loans, Long> {
}
