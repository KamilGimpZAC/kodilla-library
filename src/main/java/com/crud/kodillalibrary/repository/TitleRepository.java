package com.crud.kodillalibrary.repository;

import com.crud.kodillalibrary.domain.Title;
import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {

    @Override
    List<Title> findAll();
}
