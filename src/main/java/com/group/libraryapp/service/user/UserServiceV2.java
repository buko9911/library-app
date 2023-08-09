package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.reponse.UserResponse;
import com.group.libraryapp.dto.user.request.UserCreatRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //아래에 있는 함수가 시작될 때 start transaction을 해줌
    //함수가 에외 없이 잘 끝나면 commit
    //문제가 발생하면 rollback
    @Transactional
    public void saveUser(UserCreatRequest request){
        User u= userRepository.save(new User(request.getName(),request.getAge()));//save 되고 난 후 User에 id가 들어있음
        //u.getId(); 1 ,2,3...

    }
    @Transactional(readOnly=true)
    public List<UserResponse> getUsers(){
       List<User> users= userRepository.findAll();//sql이 자동으로 날라가 모든 user정보를 불러옴
       return users.stream().map(user -> new UserResponse(user.getId(),user.getName(),user.getAgr()))
                .collect(Collectors.toList());
    }
    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.getId())//select * from user where id=?; 코드가 자동으로 날라감,결과는 Optional<User> 객체
                .orElseThrow(()->new IllegalArgumentException());//user가 없다면 예외

        user.updateName(request.getName());
        userRepository.save(user);//바뀐것을 기준으로 자동으로 업데이트 sql이 날라감,연속성 컨텍스트 로 인해 안적어줘도 됨
    }
    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name);
        if(user==null){
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}