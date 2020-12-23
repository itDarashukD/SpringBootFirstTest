package com.example.webContent.repository;

import com.example.webContent.accesDB.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IMessageRepository extends CrudRepository<Message,Long> {

   List<Message> findByTag(String tag);

}

