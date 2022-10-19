package com.example.demo.util;

import com.example.demo.dto.Result;

public class ResultUtil
{
    public static Result getOK(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result getOK(){
        return getOK(null);
    }

    public static Result getError(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
