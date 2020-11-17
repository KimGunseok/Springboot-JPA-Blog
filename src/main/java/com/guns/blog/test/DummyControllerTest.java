package com.guns.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guns.blog.model.RoleType;
import com.guns.blog.model.User;
import com.guns.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Value("${spring.data.web.pageable.default-page-size}")
    private int pageSize;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/dummy/user")
	public List<User> userList(){
		
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴밭음
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		if(pagingUsers.isFirst()) {
			System.out.println("###Strat Page### pageSize : " + pageSize);
		} else if(pagingUsers.isLast()) {
			System.out.println("###End Page###");
		}
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터를 전달 받을 수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 null이 되면 문제 발생
		// Optional로 user객체를 감싸서 가져올테니 null인지 아닌지 판단해서 리턴
		
		// 람다식
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 사용자는 없습니다.");
//		});
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환 (웹브라우저가 이해할 수 있는 데이터 -> json(Gson라이브러리)
		//스프링부트 = MessageConverter가 응답시에 자동 작종
		//만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 JackSon라이브러리를 호출해서 Json형태로 자동 변환
		return user;
	}
	
	//http://localhost:8000/blog/dummy/join (요청)
	@PostMapping("/dummy/join")
	public String join(User user) {

		System.out.println("id : " + user.getId());
		System.out.println("userName : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}

}
