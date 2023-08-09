package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {//JpaRepository 상속 만으로 빈으로 등록됨,어노테이션 필요x

    User findByName(String name);


}//함수 이름만 작성하면 알아서 sql 조립,find->1개의 데이터 가져옴,by뒤에 붙는 필드 이름으로 select 쿼리의 where문 작성
