package com.blog.myblog.repository;

import com.blog.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User, Long> { // <User(테이블), Long(Id)>
}
