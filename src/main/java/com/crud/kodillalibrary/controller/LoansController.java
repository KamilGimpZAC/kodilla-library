package com.crud.kodillalibrary.controller;

import com.crud.kodillalibrary.domain.CopyStatus;
import com.crud.kodillalibrary.domain.Loans;
import com.crud.kodillalibrary.dto.LoansDto;
import com.crud.kodillalibrary.exception.LoanNotFound;
import com.crud.kodillalibrary.mapper.LoansMapper;
import com.crud.kodillalibrary.service.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/loans")
public class LoansController {

    private final LoansService loansService;
    private final LoansMapper loansMapper;

    @Autowired
    public LoansController(LoansService loansService, LoansMapper loansMapper) {
        this.loansService = loansService;
        this.loansMapper = loansMapper;
    }

    @PostMapping(value = "createLoan")
    public void createLoan(@RequestBody LoansDto loansDto){
        loansService.saveLoan(loansMapper.mapToLoans(loansDto));
    }

    @GetMapping(value = "getLoan")
    public LoansDto getLoan(@RequestParam Long id) throws LoanNotFound{
        return loansMapper.mapToLoansDto(loansService.getLoan(id).orElseThrow(LoanNotFound::new));
    }

    @GetMapping(value = "getLoans")
    public List<LoansDto> getLoans(){
        return loansMapper.mapToLoansDtoList(loansService.getAllLoans());
    }

    @DeleteMapping(value = "deleteLoan")
    public void deleteLoan(@RequestParam Long id){
        loansService.deleteLoan(id);
    }

    @PutMapping(value = "borrowingBook")
    public LoansDto borrowingBook(@RequestParam Long id) throws LoanNotFound{
        Loans loans = loansService.getLoan(id).orElseThrow(LoanNotFound::new);
        loansService.deleteLoan(id);
        loans.setDateOfLoan(LocalDate.now());
        loans.getCopy().setStatus(CopyStatus.BORROWED);
        loansService.saveLoan(loans);
        return loansMapper.mapToLoansDto(loans);
    }

    @PutMapping(value = "returningBook")
    public LoansDto returningBook(@RequestParam Long id) throws LoanNotFound{
        Loans loans = loansService.getLoan(id).orElseThrow(LoanNotFound::new);
        loansService.deleteLoan(id);
        loans.setDateOfReturn(LocalDate.now());
        loans.getCopy().setStatus(CopyStatus.AVAILABLE);
        loansService.saveLoan(loans);
        return loansMapper.mapToLoansDto(loans);
    }
}
