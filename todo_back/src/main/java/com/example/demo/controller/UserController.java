package com.example.demo.controller;

import com.example.demo.entity.UserRecord;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "UserController", description = "用户接口")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/getAllUser")
    @Operation(summary = "查询所有用户", description = "查询所有用户")
    public List<UserRecord> getAllUser()
    {
        return userService.getAllUsers();
    }

    @PostMapping(value="/add-user")
    @Operation(summary = "创建用户", description = "创建用户")
    public void addUser(@RequestBody UserRecord userRecord)
    {
        userService.addUser(userRecord);
    }

    @PostMapping(value="/update-user")
    @Operation(summary = "更新用户", description = "更新用户")
    public void updateUser(@RequestBody UserRecord userRecord)
    {
        userService.updateUser(userRecord);
    }

    @DeleteMapping(value="/user/{id}")
    @Operation(summary = "删除用户", description = "删除用户")
    public void updateUser(@PathVariable("id") Integer id)
    {
        userService.deleteUser(id);
    }
}
