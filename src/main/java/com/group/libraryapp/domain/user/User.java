package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.book.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=null;

    @Column(nullable =false, length=20, name="name")//=> name varcher(20)과 같은 의미,생략 가능 함
    private String name;

    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<UserLoanHistory> userLoanHistories=new ArrayList<>();

    protected  User(){}

    public User(String name, Integer agr) {
        if(name==null|| name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다",name));
        }
        this.name = name;
        this.age = agr;
    }
    public void removeOneHistory(){
        userLoanHistories.removeIf((history)->"책1".equals(history.getBookName()));
    }
//    public void removeOneHistory(){
//        userLoanHistories.removeIf(new Predicate<UserLoanHistory>(){
//            @Override
//            public boolean test(UserLoanHistory history){
//                return "책1".equals(history.getBookName());
//            }
//        }
//    );
//    }


    public String getName() {
        return name;
    }

    public Integer getAgr() {
        return age;
    }

    public Long getId(){
        return id;
    }

    public void updateName(String name){
        this.name=name;
    }

    public void loanBook(String bookName){

        this.userLoanHistories.add(new UserLoanHistory(this,bookName,false));
        System.out.println("hello");
//        for(UserLoanHistory e : userLoanHistories){
//            System.out.println(e.getUserName());
//        }
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history->history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException());
        targetHistory.doReturn();
    }
}
