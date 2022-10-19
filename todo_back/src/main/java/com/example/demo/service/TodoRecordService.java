package com.example.demo.service;

import com.example.demo.dto.TodoRecordDto;
import com.example.demo.dto.TodoRecordItemDto;
import com.example.demo.entity.TodoRecord;
import com.example.demo.entity.TodoRecordItem;
import com.example.demo.repository.TodoRecordRepository;
import java.beans.PropertyDescriptor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

@Service
@Transactional public class TodoRecordService
{

    private final TodoRecordRepository todoRecordRepository;

    public TodoRecordService(TodoRecordRepository todoRecordRepository)
    {
        this.todoRecordRepository = todoRecordRepository;
    }

    public List<TodoRecordDto> getAllTodoRecords() throws Exception
    {
        List<TodoRecord> todoRecords = new ArrayList<>();
        todoRecordRepository.findAll().forEach(todoRecords::add);
        List<TodoRecordDto> todoRecordDtos = todoRecords.stream()
                .map(r -> {
                    TodoRecordDto todoRecordDto = new TodoRecordDto();
                    todoRecordDto.setTodoRecordItems(r.getTodoRecordItems()
                                                             .stream()
                                                             .map(i -> {
                                                                 TodoRecordItemDto todoRecordItemDto = new TodoRecordItemDto();
                                                                 todoRecordItemDto.setDescription(i.getDescription());
                                                                 todoRecordItemDto.setCompleted(i.getCompleted());
                                                                 todoRecordItemDto.setId(i.getId());
                                                                 return todoRecordItemDto;
                                                             })
                                                             .collect(Collectors.toList()));
                    if ( r.getTodoDay() != null )
                    {
                        todoRecordDto.setTodoDay(r.getTodoDay());
                    }

                    todoRecordDto.setId(r.getId());
                    return todoRecordDto;
                })
                .collect(Collectors.toList());

        return todoRecordDtos;
    }

    public void addTodoRecord(TodoRecordDto todoRecordDto) throws Exception
    {
        TodoRecord todoRecord = new TodoRecord();
        todoRecord.setTodoRecordItems(todoRecordDto.getTodoRecordItems()
                                              .stream()
                                              .map(i -> {
                                                  TodoRecordItem todoRecordItem = new TodoRecordItem();
                                                  todoRecordItem.setDescription(i.getDescription());
                                                  todoRecordItem.setTodoRecord(todoRecord);
                                                  return todoRecordItem;
                                              })
                                              .collect(Collectors.toList()));
        todoRecord.setTodoDay(todoRecordDto.getTodoDay());
        todoRecordRepository.save(todoRecord);
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for( PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void updateTodoRecord(TodoRecord todoRecord)
    {
        Optional<TodoRecord> todoRecordOptional = todoRecordRepository.findById(todoRecord.getId());
        if ( todoRecordOptional.isPresent() )
        {
            TodoRecord todoRecordFind = todoRecordOptional.get();
            //todo
            todoRecordRepository.save(todoRecordFind);
        }
    }

    public void deleteTodoRecord(Integer id)
    {
        todoRecordRepository.deleteById(id);
    }
}
