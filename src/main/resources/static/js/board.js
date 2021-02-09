let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json;charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(erro) {
			alert(JSON.stringify(erro));
		});
	},
	
		deleteById: function() {
			var id =  $("#id").text();
			
			$.ajax({
				type: "DELETE",
				url: "/api/board/"+id,
				dataType: "json"
			}).done(function(resp) {
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}).fail(function(erro) {
				alert(JSON.stringify(erro));
			});
		},
}
index.init();