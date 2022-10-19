package com.example.demo.controller;

import com.example.demo.annotation.EnableFilter;
import com.example.demo.dto.Result;
import com.example.demo.dto.TodoRecordDto;
import com.example.demo.entity.TodoRecord;
import com.example.demo.entity.TodoRecordItem;
import com.example.demo.service.TodoRecordItemService;
import com.example.demo.service.TodoRecordService;
import com.example.demo.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "TodoRecordController", description = "清单接口")
public class TodoRecordController
{

    private final TodoRecordService todoRecordService;

    private final TodoRecordItemService todoRecordItemService;

    public TodoRecordController(TodoRecordService todoRecordService,TodoRecordItemService todoRecordItemService)
    {
        this.todoRecordService = todoRecordService;
        this.todoRecordItemService = todoRecordItemService;
    }

    @EnableFilter
    @GetMapping("/getAllTodoRecord")
    @Operation(summary = "查询所有清单", description = "查询所有清单",security = { @SecurityRequirement(name = "TENANT-ID") })
    public Result getAllTodoRecords() throws Exception
    {
        List<TodoRecordDto> allTodoRecords = todoRecordService.getAllTodoRecords();
        return ResultUtil.getOK(allTodoRecords);
    }

    @PostMapping(value="/add-todoRecord")
    @Operation(summary = "创建清单", description = "创建清单",security = { @SecurityRequirement(name = "TENANT-ID") })
    public Result addTodoRecord(@RequestBody TodoRecordDto todoRecordDto) throws Exception
    {
        todoRecordService.addTodoRecord(todoRecordDto);
        return ResultUtil.getOK();
    }

    @PostMapping(value="/update-todoRecord")
    @Operation(summary = "更新清单", description = "更新清单")
    public Result updateTodoRecord(@RequestBody TodoRecord todoRecord)
    {
        todoRecordService.updateTodoRecord(todoRecord);
        return ResultUtil.getOK();
    }

    @DeleteMapping(value="/todoRecord/{id}")
    @Operation(summary = "删除清单", description = "删除清单")
    public Result deleteTodoRecord(@PathVariable("id") Integer id)
    {
        todoRecordService.deleteTodoRecord(id);
        return ResultUtil.getOK();
    }

    @PostMapping(value="/update-todoRecordItem")
    @Operation(summary = "更新单个任务", description = "更新单个任务")
    public Result updateTodoRecord(@RequestBody TodoRecordItem todoRecordItem)
    {
        todoRecordItemService.updateTodoRecordItem(todoRecordItem);
        return ResultUtil.getOK();
    }
}
