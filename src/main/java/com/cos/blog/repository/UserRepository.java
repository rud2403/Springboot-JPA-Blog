package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	// SELECT * FROM user WHERE username = 1?;
	Optional<User> findByUsername(String username);
}

// 자동으로 bean 등록이 돼서 @Repository 생략 가능하다.

//JPa Naming 쿼리
//findByUsernameAndPassword = SELECT * FROM user WHERE username = ? AND password = ?;
//User findByUsernameAndPassword(String username, String password);