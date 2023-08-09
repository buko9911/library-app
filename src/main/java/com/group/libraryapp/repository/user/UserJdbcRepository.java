package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.reponse.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcRepository {//저장소,DB에 접근해 sql을 날리는 clas

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name,Integer age){
        String sql="insert into user (name,age) values (?,?)";
        jdbcTemplate.update(sql,name,age);
    }

    public List<UserResponse> getUsers(){
        String sql="select * from User";
        return jdbcTemplate.query(sql,new RowMapper<UserResponse>(){
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id=rs.getLong("id");
                String name=rs.getString("name");
                int age=rs.getInt("age");
                return new UserResponse(id,name,age);
            }
        });
    }

    public boolean isUserNotExist(long id){
        String readSql = "select * from user where id= ?";
        return jdbcTemplate.query(readSql, new RowMapper<Integer>(){//(rs,rowNum)->0으로 바꿀수 있음
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {//찾는 id가 존재 하면 0반환
                        return 0;
                    }
                }
                ,id).isEmpty();//존재 한다면 0이 나와 반환한 List가 비어있지 않을 것 -> isUserNotExist가 false가 됨

    }
    public void updateUsername(String name,long id){
        String sql="update user set name = ? where id = ?";//해당 id 의 name을 ?로 바꿈
        jdbcTemplate.update(sql,name,id);
    }

    public boolean isUserNotExist(String name){
        String readSql = "select * from user where name= ?";
        return jdbcTemplate.query(readSql, new RowMapper<Integer>(){//(rs,rowNum)->0으로 바꿀수 있음
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {//찾는 id가 존재 하면 0반환
                        return 0;
                    }
                }
                ,name
        ).isEmpty();//존재 한다면 0이 나와 반환한 List가 비어있지 않을 것 -> isUserNotExist가 false가 됨

    }
    public void deleteUser(String name){
        String sql="delete from user where name= ?";
        jdbcTemplate.update(sql,name);
    }

}
