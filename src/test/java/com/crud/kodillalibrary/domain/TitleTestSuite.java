package com.crud.kodillalibrary.domain;

import com.crud.kodillalibrary.exception.TitleNotFound;
import com.crud.kodillalibrary.repository.CopyRepository;
import com.crud.kodillalibrary.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TitleTestSuite {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Test
    public void testCreate(){
        //Given
        Title title = new Title();
        //When
        titleRepository.save(title);
        //Then
        assertEquals(1,titleRepository.count());
        //Cleanup
        try{
            titleRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }

    }

    @Test
    public void testDelete(){
        //Given
        Title title = new Title();
        //When&Then
        titleRepository.save(title);
        assertEquals(1,titleRepository.count());
        titleRepository.deleteById(title.getId());
        assertEquals(0,titleRepository.count());
    }

    @Test
    public void testRead(){
        //Given
        Title title = new Title();
        //When
        titleRepository.save(title);
        Optional<Title> output = titleRepository.findById(title.getId());
        //Then
        assertTrue(output.isPresent());
        //Cleanup
        try{
            titleRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testUpdate() throws TitleNotFound{
        //Given
        Title title = new Title();
        //When
        titleRepository.save(title);
        Title output = titleRepository.findById(title.getId()).orElseThrow(TitleNotFound::new);
        output.setTitle("Test");
        //Then
        assertEquals("Test", output.getTitle());
        //Cleanup
        try{
            titleRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }

    @Test
    public void testRelations(){
        //Given
        Title title = new Title();
        Copy copy1 = new Copy();
        Copy copy2 = new Copy();
        List<Copy> copyList = new ArrayList<>();
        copyList.add(copy1);
        copyList.add(copy2);
        title.setCopies(copyList);
        //When
        titleRepository.save(title);
        //Then
        assertEquals(1,titleRepository.count());
        assertEquals(2,copyRepository.count());
        //Cleanup
        try {
            titleRepository.deleteAll();
            copyRepository.deleteAll();
        } catch (Exception e){
            //do nothing
        }
    }
}