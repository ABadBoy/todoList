package com.example.demo.controller;

import com.example.demo.dto.Result;
import com.example.demo.entity.UserRecord;
import com.example.demo.service.UserService;
import com.example.demo.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Tag(name = "LoginController", description = "登录接口")
public class LoginController
{

    private final UserService userService;

    public LoginController(UserService userService)
    {
        this.userService = userService;
    }


    @PostMapping(value="/sign-in")
    @Operation(summary = "登录", description = "登录")
    public Result signIn(@RequestBody UserRecord userRecord)
    {
        String password = DigestUtils.md5DigestAsHex(userRecord.getPassword()
                                                             .getBytes(StandardCharsets.UTF_8));
        UserRecord userRecordFind = userService.findByNameAndPassword(userRecord.getName(),password);
        if ( userRecordFind != null){
            return ResultUtil.getOK(userRecordFind);
        }
        return null;
    }

    @PostMapping(value="/sign-up")
    @Operation(summary = "注册", description = "注册")
    public Result signUp(@RequestBody UserRecord userRecord) throws Exception
    {
        UserRecord userRecordFind = userService.findByName(userRecord.getName());
        if ( userRecordFind != null){
            throw new Exception("user name already exist!");
        }
        String password = DigestUtils.md5DigestAsHex(userRecord.getPassword()
                                                      .getBytes(StandardCharsets.UTF_8));
        userRecord.setPassword(password);
        userService.addUser(userRecord);
        return ResultUtil.getOK();
    }

}
