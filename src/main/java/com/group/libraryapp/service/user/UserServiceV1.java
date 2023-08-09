package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.reponse.UserResponse;
import com.group.libraryapp.dto.user.request.UserCreatRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreatRequest request){
        userJdbcRepository.saveUser(request.getName() ,request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request){

        String readSql = "select * from user where id= ?";
        boolean isUserNotExist= userJdbcRepository.isUserNotExist(request.getId());
        if(isUserNotExist){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.updateUsername(request.getName(),request.getId());
    }

    public void deleteUser(String name){

        if(userJdbcRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }
        userJdbcRepository.deleteUser(name);
    }



}