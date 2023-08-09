package com.group.libraryapp.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory,Long> {
    boolean existsByBookNameAndIsReturn(String name,boolean isReturn);
    UserLoanHistory findByUserIdAndBookName(Long id,String name);
}