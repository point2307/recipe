var rawCount = 1;
function addRaws(){
    rawCount +=1
    let mater = $('#searchBox').val();
    let amount= $('#rawAmount').val();

    $('#materList').append('<li id="raw'+rawCount+'"><a href="#" onclick="deleteThis('+rawCount+
        ')">'+mater+':'+amount+'</a><input type="hidden" name="mater" value="'+mater+'"/>' +
        '<input type="hidden" name="rawsize" value="'+amount+'"/></li>')

    $('#searchBox').val("");
    $('#rawAmount').val("");
}

let procIndex = 15
function plusProc() {

    $('#process').append("<div id='index"+procIndex+"'>" +
        "<div class='input-group mb-3'>" +
        "        <label class='input-group-text' for='inputGroupFile01'>사 진</label>" +
        "        <input type='file' multiple name='procImg' class='form-control' id='procimg'>" +
        "    </div>" +
        "        <div class='mb-3'>" +
        "        <textarea class='form-control' name='procDetail' id='exampleFormControlTextarea1' placeholder='조리방법' rows='3'>"+
    "</textarea>" +
    "    </div><br>" +
    "    <button type='button' onclick='procDel("+procIndex+")'>삭제하기</button><br><hr> "+
    "</div>"
)
    ;
    procIndex+=1;
}
procDel = (e) =>{
    $('#index'+e).empty();
}

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



function likeRecipe(e) {
    console.log(e);
 $.ajax({
     url:'/recipe/likeRecipe',
     type:'get',
     async:true,
     data: {data: e},
     dataType: 'text',
     success(data){
         console.log(data)
         if(data == 1){
             $('#'+e).load(location.href+' #'+e);
             $('#likeDiv').load(location.href+' #likeDiv')
         } else{
             alert('좋아요는 로그인 후 사용 가능합니다.!')
         }

     }
 })

}

function notlikeRecipe(e) {
    console.log(e);
    $.ajax({
        url:'/recipe/notlikeRecipe',
        type:'get',
        async:true,
        data: {data: e},
        dataType: 'text',
        success(data){
            $('#'+e).load(location.href+' #'+e);
            $('#likeDiv').load(location.href+' #likeDiv');
        }
    })

}
function deleteThis(e){
    $("#raw"+e).empty();
}

function deleteraws(e){
    $('#'+e).empty();
}

function makeReply(){
    let recipe= $('#recipe').val();
    let content = $('#content').val();
    console.log(content)
    $.ajax({
        url: '/recipe/makeReply',
        type: 'post',
        async: false,
        data: {recipe:recipe, content:content},
        dataType: 'text',
        success(data){
            console.log(data)
            if(data == 1){
                $('#replyList').load(location.href+' #replyList')
            } else{
                alert('로그인 하셔야 댓글작성이 가능합니다.')
            }
        }
    })
}

function deleteReply(e){
    $.ajax({
        url: '/recipe/deleteReply',
        type: 'post',
        async: true,
        data: {id: e},
        dataType: 'text',
        success(data){
             alert('삭제되었습니다.');
            $('#replyList').load(location.href+' #replyList')
        }
    })
}
updateReplyForm = (e) => {
    $('#'+e).append(" <div class=\"input-group\">\n" +
        "              <textarea class=\"form-control\" id=\"content"+e+"\" cols=\"20\" rows=\"3\"></textarea>\n" +
        "              <button class=\"input-group-text\" onclick=\"updateReply("+e+")\">댓글수정</button>\n" +
        "          </div>"

    )
}


updateReply = (e) =>{

    let content = $('#content'+e).val();
    $.ajax({
        url: '/recipe/updateReply',
        type: 'post',
        async: false,
        data: {id:e, content:content},
        dataType: 'text',
        success(data){
            console.log(data)
            if(data == 1){
                $('#replyList').load(location.href+' #replyList')
            } else{
                alert('로그인 하셔야 댓글작성이 가능합니다.')
            }
        }
    })
}

deleteRecipeCheck = () => {
    $('#delete').modal('show');
}

deleteRecipe = (e) => {
    $.ajax({
        url: '/recipe/deleteRecipe',
        type: 'get',
        data: {id:e},
        dataType: 'text',
        success(data) {
            if(data == 1){
                location.href="/common/recipeList"
            }
        }
    })
}