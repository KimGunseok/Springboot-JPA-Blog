/**
 * 회원가입
 */
let index = {
	init: function(){
		$("#btn-save").on("click",()=>{// function(){}, ()=>{} this를 바인딩하기 위해서 사용
			this.save();
		});
		/*$("#btn-login").on("click",()=>{// function(){}, ()=>{} this를 바인딩하기 위해서 사용
			this.login();
		});*/
	},
	
	save: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 데이터를 json으로 변경하여 insert요청
		//ajax 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 결과 타입
		}).done(function(resp){
			// 성공
			alert("회원가입이 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			// 실패
			alert(JSON.stringify(error));
		});
	},
	
	login: function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		//ajax 호출시 default가 비동기 호출
		//ajax 통신을 이용해서 데이터를 json으로 변경하여 insert요청
		//ajax 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			// 로그인 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
			dataType: "json" //요청을 서버로해서 응답이 왔을 때 결과 타입
		}).done(function(resp){
			// 성공
			alert("로그인이 완료되었습니다.")
			location.href="/";
		}).fail(function(error){
			// 실패
			alert(JSON.stringify(error));
		});
	}
}

index.init();