package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice // 모든 Exception 발생시 여기 class로 온다.
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=IllegalArgumentException.class) // IllegalArgumentException만 받기위한 필터
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
}
