package com.example.demo.dto;

import lombok.Data;

@Data
public class TodoRecordItemDto
{
    private Integer id;

    private String description;

    private Boolean completed;

    public TodoRecordItemDto() { }

    public TodoRecordItemDto(String description)
    {
        this.description = description;
    }
}
