package com.example.demo.repository;

import com.example.demo.entity.TodoRecord;
import org.springframework.data.repository.CrudRepository;

public interface TodoRecordRepository
        extends CrudRepository<TodoRecord,Integer>
{

}
