package com.crud.kodillalibrary.domain;

import com.crud.kodillalibrary.exception.ReaderNotFound;
import com.crud.kodillalibrary.exception.TitleNotFound;
import com.crud.kodillalibrary.repository.LoansRepository;
import com.crud.kodillalibrary.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReaderTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private LoansRepository loansRepository;


    @Test
    public void testCreate(){
        //Given
        Reader reader = new Reader();
        //When
        readerRepository.save(reader);
        //Then
        assertEquals(1,readerRepository.count());
        //Cleanup
        try {
            readerRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testDelete(){
        //Given
        Reader reader = new Reader();
        //When&Then
        readerRepository.save(reader);
        assertEquals(1,readerRepository.count());
        readerRepository.deleteById(reader.getId());
        assertEquals(0,readerRepository.count());
    }

    @Test
    public void testRead(){
        //Given
        Reader reader = new Reader();
        //When
        readerRepository.save(reader);
        Optional<Reader> output = readerRepository.findById(reader.getId());
        //Then
        assertTrue(output.isPresent());
        //Cleanup
        try{
            readerRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testUpdate() throws ReaderNotFound {
        //Given
        Reader reader = new Reader();
        //When
        readerRepository.save(reader);
        Reader output = readerRepository.findById(reader.getId()).orElseThrow(ReaderNotFound::new);
        output.setName("Test");
        //Then
        assertEquals("Test", output.getName());
        //Cleanup
        try{
            readerRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testRelations(){
        //Given
        Reader reader = new Reader();
        Loans loans1 = new Loans();
        Loans loans2 = new Loans();
        List<Loans> loansList = new ArrayList<>();
        loansList.add(loans1);
        loansList.add(loans2);
        //When
        reader.setLoans(loansList);
        readerRepository.save(reader);
        //Then
        assertEquals(1,readerRepository.count());
        assertEquals(2,loansRepository.count());
        //Cleanup
        try {
            readerRepository.deleteAll();
            loansRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}