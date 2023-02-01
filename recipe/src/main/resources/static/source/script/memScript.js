function idCheck(){
        const id = $("#userId").val();
        $.ajax({
                type: "get",
                async: false,
                url: "http://localhost:8080/member/idCheck",
                data: {id: id},
                success: function (data) {
                        if(data == 1) {
                                $("#message").text("이미 사용중인 ID 입니다.");
                        }else {
                                $("#message").text("사용 가능한 ID 입니다.");
                        }
                }
        })
};