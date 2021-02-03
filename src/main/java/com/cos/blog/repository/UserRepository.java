package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
// 자동으로 bean 등록이 돼서 @Repository 생략 가능하다.
}
