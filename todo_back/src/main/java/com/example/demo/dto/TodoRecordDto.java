package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class TodoRecordDto
{
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private LocalDate todoDay;

    private List<TodoRecordItemDto> todoRecordItems = new ArrayList<>();
}
