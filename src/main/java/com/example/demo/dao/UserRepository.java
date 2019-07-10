package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameEquals(String username);


    @Query(value = "select * from user_t where useame=?1", nativeQuery = true)
    User findUser(String userName);

    @Query(value = "select user.username,user.password from User user where user.username=?1")
    AbstractUser getabs(String userName);
}
