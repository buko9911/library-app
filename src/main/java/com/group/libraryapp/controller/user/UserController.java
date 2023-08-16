package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.reponse.UserResponse;
import com.group.libraryapp.dto.user.request.UserCreatRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserServiceV2 userService;

    @Autowired
    public UserController(UserServiceV2 userService){//얘는 언제 시행??
        this.userService = userService;
    }

    @PostMapping("/user")//POST /user  path가 user임
    public void saveUser(@RequestBody UserCreatRequest request){
        userService.saveUser(request);
    }
    @GetMapping("/user")
    public List<UserResponse> getUser(){
       return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
       userService.deleteUser(name);
    }

}
