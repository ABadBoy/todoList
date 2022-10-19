package com.example.demo.config;

import com.example.demo.dto.Result;
import com.example.demo.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public Result handle(Exception e){
        log.error(e.getMessage(),e);
        return ResultUtil.getError(1, e.getMessage());
    }
}
