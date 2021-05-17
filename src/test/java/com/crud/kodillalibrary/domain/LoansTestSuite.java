package com.crud.kodillalibrary.domain;

import com.crud.kodillalibrary.exception.CopyNotFound;
import com.crud.kodillalibrary.exception.LoanNotFound;
import com.crud.kodillalibrary.repository.CopyRepository;
import com.crud.kodillalibrary.repository.LoansRepository;
import com.crud.kodillalibrary.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoansTestSuite {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void testCreate(){
        //Given
        Loans loans = new Loans();
        //When
        loansRepository.save(loans);
        //Then
        assertEquals(1,loansRepository.count());
        //Cleanup
        try {
            loansRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testDelete(){
        //Given
        Loans loans = new Loans();
        //When&Then
        loansRepository.save(loans);
        assertEquals(1,loansRepository.count());
        loansRepository.deleteById(loans.getId());
        assertEquals(0,loansRepository.count());
    }

    @Test
    public void testRead(){
        //Given
        Loans loans = new Loans();
        //When
        loansRepository.save(loans);
        Optional<Loans> output = loansRepository.findById(loans.getId());
        //Then
        assertTrue(output.isPresent());
        //Cleanup
        try{
            loansRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testUpdate() throws LoanNotFound {
        //Given
        Loans loans = new Loans();
        //When
        loansRepository.save(loans);
        Loans output = loansRepository.findById(loans.getId()).orElseThrow(LoanNotFound::new);
        output.setDateOfLoan(LocalDate.now());
        //Then
        assertEquals(LocalDate.now(), output.getDateOfLoan());
        //Cleanup
        try{
            loansRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testRelations(){
        //Given
        Loans loans = new Loans();
        Copy copy = new Copy();
        Reader reader = new Reader();
        loans.setCopy(copy);
        loans.setReader(reader);
        //When
        loansRepository.save(loans);
        //Then
        assertEquals(1,loansRepository.count());
        assertEquals(1,copyRepository.count());
        assertEquals(1,readerRepository.count());
        //Cleanup
        try {
            loansRepository.deleteAll();
            copyRepository.deleteAll();
            readerRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}