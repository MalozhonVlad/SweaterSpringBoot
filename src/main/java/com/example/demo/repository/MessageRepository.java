package com.example.demo.repository;

import com.example.demo.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

//    List<Message> findByTag(String tag);

    Page<Message> findByTag(String tag, Pageable pageable);

    Page<Message> findAll(Pageable pageable);

}
