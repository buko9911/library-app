package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.book.UserLoanHistory;
import com.group.libraryapp.domain.book.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,UserLoanHistoryRepository userLoanHistoryRepository,UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository=userLoanHistoryRepository;
        this.userRepository=userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
     bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){

        User user= userRepository.findByName(request.getUserName());
        Book book=bookRepository.findByName(request.getBookName());
        if(user==null||book==null){
            throw new IllegalArgumentException();
        }
        boolean exist = userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(),false);
        if(exist==true){
            throw new IllegalArgumentException();
        }

//        if(userLoanHistory!=null&&userLoanHistory.getIsReturn()==false){//userLoanHistory가 null 인경우 NPE발생..
//
//            throw new IllegalArgumentException("대출 불가");
//        }

        user.loanBook(request.getBookName());

        //userLoanHistoryRepository.save(new UserLoanHistory(user,request.getBookName(),false));
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        User user= userRepository.findByName(request.getUserName());
        Book book=bookRepository.findByName(request.getBookName());
        if(user==null||book==null){
            throw new IllegalArgumentException();
        }

//        UserLoanHistory userLoanHistory= userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName());
//        if(userLoanHistory!=null){
//            userLoanHistory.doReturn();
//            userLoanHistoryRepository.save(userLoanHistory);// transactional의 연속성 컨테스트의 변경 감지 기능으로 인해 안써줘도 됨
//        }
//        else{
//            throw new IllegalArgumentException();
//        }
        user.returnBook(request.getBookName());
    }

}
