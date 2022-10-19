package com.example.demo.service;

import com.example.demo.entity.TodoRecordItem;
import com.example.demo.repository.TodoRecordItemRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TodoRecordItemService
{

    private final TodoRecordItemRepository todoRecordItemRepository;

    public TodoRecordItemService(TodoRecordItemRepository todoRecordItemRepository)
    {
        this.todoRecordItemRepository = todoRecordItemRepository;
    }

    public void updateTodoRecordItem(TodoRecordItem todoRecordItem)
    {
        Optional<TodoRecordItem> todoRecordItemOptional = todoRecordItemRepository.findById(todoRecordItem.getId());
        if ( todoRecordItemOptional.isPresent() )
        {
            TodoRecordItem todoRecordItemFind = todoRecordItemOptional.get();
            todoRecordItemFind.setCompleted(!todoRecordItem.getCompleted());
            todoRecordItemRepository.save(todoRecordItemFind);
        }
    }
}
