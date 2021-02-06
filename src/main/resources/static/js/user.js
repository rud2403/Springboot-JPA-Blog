let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){}대신 ()=>{} this를 사용하는 이유, 바인딩 하기 위해서
			this.save();
		});
		$("#btn-login").on("click", () => { // function(){}대신 ()=>{} this를 사용하는 이유, 바인딩 하기 위해서
			this.login();
		});
	},

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//console.log(data);

		$.ajax({ // ajax 호출시 default가 비동기 호출
			type: "POST",
			url: "/blog/api/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json;charset=utf-8", // body데이터가 어떤 타입인지
			dataType: "json"
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");
			//console.log(resp);
			location.href = "/blog";
		}).fail(function(erro) {
			alert(JSON.stringify(erro));
		}); // ajax 통신을 이용하여 3개의 데이터(username, password, email)를 json으로 변경하여 insert 요청
	},
	
	login: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};

		$.ajax({
			type: "POST",
			url: "/blog/api/user/login",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json;charset=utf-8", // body데이터가 어떤 타입인지
			dataType: "json"
		}).done(function(resp) {
			alert("로그인이 완료되었습니다.");
			location.href = "/blog";
		}).fail(function(erro) {
			alert(JSON.stringify(erro));

		}); // ajax 통신을 이용하여 3개의 데이터(username, password, email)를 json으로 변경하여 insert 요청
	}
}
index.init();