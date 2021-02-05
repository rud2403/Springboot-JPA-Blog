package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController // html파일이 아니라 data를 리턴해주는 controller = RestController
public class DummyControllerTest {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}		
		return "삭제되었습니다.id:" +id;
	}
	

	   @Transactional //Transaction을 걸면 save를 하지않아도 update가 된다.
	   @PutMapping("/dummy/user/{id}")
	   public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
	      System.out.println("id : "+id);
	      System.out.println("password : "+requestUser.getPassword());
	      System.out.println("email : "+requestUser.getEmail());
	      
	      User user = userRepository.findById(id).orElseThrow(()-> {
	         return new IllegalArgumentException("수정에 실패하였습니다.");
	      });
	      user.setPassword(requestUser.getPassword());
	      user.setEmail(requestUser.getEmail());
	      
	      // userRepository.save(user);
	      
	      //더티 체킹
	      return user;
	   }
	   
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
	}	
	
	@GetMapping("/dummy/user/{id}") // {id} -> 주소로 파라메터를 전달 받을 수 있음. ex)http://localhost:8000/blog/dummy/user/3
	public User detail(@PathVariable int id) {
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
			}
		});
		
		// user 객체 = 자바 오브젝트
		return user;
	}
	
	@PostMapping("/dummy/join") // http://localhost:8000/blog/dummy/join 요청
	public String join(User user) { 
		// http의 body에 username, pssword, email 데이터를 가지고 요청
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		}
}