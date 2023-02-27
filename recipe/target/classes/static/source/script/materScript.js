$(function() {
    $('#searchBox').autocomplete({
        source : function (request, response) {
            $.ajax({
                type :'get',
                url: 'http://localhost:8080/recipe/searchMater',
                data: {value : request.term},
                dataType:'json',
                success: function (data) {
                    response(
                        $.map(data, function(item){
                            console.log(data)
                            return {
                                value:item.materName
                            }
                        })
                    );
                }
            });
        },
        select: function(event, ui){

        },
        focus: function (event, ui){
            return false;
        },

        minLength : 1,
        autoFocus : true,
        classes : {
            'ul-autocomplete': 'highlight'
        },
        delay: 600,
        position : {my : 'right top', at : 'right bottom'},
        close : function(event){
            console.log(event);
        }
    }).autocomplete('instance')._renderItem = function(ul, item) { // UI 변경 부
        return $('<li>') //기본 tag가 li
            .append('<div>' + item.value + '</div>') // 원하는 모양의 HTML 만들면 됨
            .appendTo(ul);
    };
})
var materId = ""
function categoryFirst(e){
    $('#mater').empty();
    if(e==0){
        $('#categoryBig').append('<li><a href="#" onclick="categorySecond(1)">곡류</a></li>' +
            '<li><a href="#" onclick="categorySecond(2)">서류</a></li>' +
            '<li><a href="#" onclick="categorySecond(3)">두류</a></li>' +
            '<li><a href="#" onclick="categorySecond(4)">견과종실류</a></li>' +
            '<li><a href="#" onclick="categorySecond(5)">과일류</a></li>' +
            '<li><a href="#" onclick="categorySecond(6)">채소류</a></li>'

        )
    }
    if(e==1){
        $('#categoryBig').append('<li><a href="#" onclick="categorySecond(101)">축산물</a></li>'+
            '<li><a href="#" onclick="categorySecond(102)">수산물</a></li>' +
            '<li><a href="#" onclick="categorySecond(103)">기타동물</a></li>'

        )
    }
}

function categorySecond(e){
    $('#categoryBig').empty();
    if(e==101){
        $('#categoryMiddle').append('<li><a href="#" onclick="categoryThird(1010101)">소</a></li>' +
            '<li><a href="#" onclick="categoryThird(1010102)">돼지</a></li>' +
            '<li><a href="#" onclick="categoryThird(1010106)">닭</a></li>')
    }
}

function  categoryThird(e){
    $('#categoryMiddle').empty();
    $.ajax({
        url: '/myPage/categoryMater',
        type: 'get',
        data:{data:e},
        dataType: 'json',
        success(data){
            $.each(data, function(index, mat) {
              $('#selectMater').append('<li><a href="#" onclick="choiceMater('+mat.matId+')">'+mat.materName+'</a></li>'
              )
            })
        }
        }

    )
}

deleteMater = (e) =>{
    $.ajax({
        url:'/myPage/deleteMater',
        type:'get',
        data: {Id:e},
        dataType:'text',
        success(data){
            location.reload();
        }

    })
}


function choiceMater(e){
    $.ajax({
        url:'/myPage/insertMater',
        type: 'get',
        data: {data:e},
        dataType: 'text',
        success(data){
            if(data==0){
                alert('등록에 실패했습니다.')
            } else{
                alert('등록에 성공했습니다.');
                location.reload();
            }

        }
    })
}