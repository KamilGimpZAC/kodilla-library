package com.crud.kodillalibrary.service;

import com.crud.kodillalibrary.domain.Loans;
import com.crud.kodillalibrary.domain.Reader;
import com.crud.kodillalibrary.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoansService {

    private final LoansRepository loansRepository;

    public List<Loans> getAllLoans(){
        return loansRepository.findAll();
    }

    public Loans saveLoan(final Loans loans){
        return loansRepository.save(loans);
    }

    public Optional<Loans> getLoan(final Long id){
        return loansRepository.findById(id);
    }

    public void deleteLoan(final Long id){
        loansRepository.deleteById(id);
    }
}
