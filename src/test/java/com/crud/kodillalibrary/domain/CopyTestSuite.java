package com.crud.kodillalibrary.domain;

import com.crud.kodillalibrary.exception.CopyNotFound;
import com.crud.kodillalibrary.exception.ReaderNotFound;
import com.crud.kodillalibrary.repository.CopyRepository;
import com.crud.kodillalibrary.repository.LoansRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CopyTestSuite {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private LoansRepository loansRepository;

    @Test
    public void testCreate(){
        //Given
        Copy copy = new Copy();
        //When
        copyRepository.save(copy);
        //Then
        assertEquals(1,copyRepository.count());
        //Cleanup
        try {
            copyRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testDelete(){
        //Given
        Copy copy = new Copy();
        //When&Then
        copyRepository.save(copy);
        assertEquals(1,copyRepository.count());
        copyRepository.deleteById(copy.getId());
        assertEquals(0,copyRepository.count());
    }

    @Test
    public void testRead(){
        //Given
        Copy copy = new Copy();
        //When
        copyRepository.save(copy);
        Optional<Copy> output = copyRepository.findById(copy.getId());
        //Then
        assertTrue(output.isPresent());
        //Cleanup
        try{
            copyRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testUpdate() throws CopyNotFound {
        //Given
        Copy copy = new Copy();
        //When
        copyRepository.save(copy);
        Copy output = copyRepository.findById(copy.getId()).orElseThrow(CopyNotFound::new);
        output.setStatus(CopyStatus.AVAILABLE);
        //Then
        assertEquals(CopyStatus.AVAILABLE, output.getStatus());
        //Cleanup
        try{
            copyRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testRelations(){
        //Given
        Copy copy = new Copy();
        Title title = new Title();
        copy.setTitle(title);
        Loans loans1 = new Loans();
        Loans loans2 = new Loans();
        List<Loans> loans = new ArrayList<>();
        loans.add(loans1);
        loans.add(loans2);
        copy.setLoans(loans);
        //When
        copyRepository.save(copy);
        //Then
        assertEquals(1,copyRepository.count());
        assertEquals(1,titleRepository.count());
        assertEquals(2,loansRepository.count());
        //Cleanup
        try {
            copyRepository.deleteAll();
            titleRepository.deleteAll();
            loansRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}