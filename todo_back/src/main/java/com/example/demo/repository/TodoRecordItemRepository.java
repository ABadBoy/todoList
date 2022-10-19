package com.example.demo.repository;

import com.example.demo.entity.TodoRecordItem;
import org.springframework.data.repository.CrudRepository;

public interface TodoRecordItemRepository
        extends CrudRepository<TodoRecordItem,Integer>
{

}
