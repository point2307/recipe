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
                                $('#reid').prop('value',id);
                        }
                }
        })
};

function registerCheck(){
        if($('#userId').val() == ""){
                alert('아이디는 반드시 입력하셔야 합니다')
                $('#userId').focus()
                return false
        }
        if($('#reid').val() == ""){
                alert('중복확인을 해주세요')
                $('#checkbtn').focus()
                return false
        }
        if($('#row_pass').val().length < 6 || $('#row_pass').val().length > 12 ){
                alert('비밀번호는 6~12자 사이로 입력하셔야 합니다')
                $('#row_pass').focus()
                return false
        }
        if($('#passwordCheck').val() != $('#row_pass').val()){
                alert('비밀번호와 비밀번호 확인은 같아야 합니다')
                $('#passwordCheck').focus()
                return false
        }

        if($('#nickName').val() == ""){
                alert('닉네임은 반드시 입력하셔야 합니다')
                $('#nickName').focus()
                return false
        }
        if($('#name').val() == ""){
                alert('이름은 반드시 입력하셔야 합니다')
                $('#name').focus()
                return false
        }
        if($('#ema').val() == ""){
                alert('이메일은 반드시 입력하셔야 합니다')
                $('#ema').focus()
                return false
        }

        if($('#il').val() == ""){
                alert('이메일은 반드시 입력하셔야 합니다')
                $('#il').focus()
                return false
        }
        if($('#address').val() == ""){
                alert('주소는 반드시 입력하셔야 합니다')
                $('#address').focus()
                return false
        }
        return true
}

function updateCheck(){
        if($('#row_pass').val().length < 6 || $('#row_pass').val().length > 12 ){
                alert('비밀번호는 6~12자 사이로 입력하셔야 합니다')
                $('#row_pass').focus()
                return false
        }
        if($('#passwordCheck').val() != $('#row_pass').val()){
                alert('비밀번호와 비밀번호 확인은 같아야 합니다')
                $('#passwordCheck').focus()
                return false
        }
        if($('#nickName').val() == ""){
                alert('닉네임은 반드시 입력하셔야 합니다')
                $('#nickName').focus()
                return false
        }
        if($('#address').val() == ""){
                alert('주소는 반드시 입력하셔야 합니다')
                $('#address').focus()
                return false
        }
        return true
}

function deleteCheck(){
        $('#delete').modal('show');
}