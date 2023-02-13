var rawCount = 1;
function addRaws(){
    rawCount +=1
    let mater = $('#searchBox').val();
    let amount= $('#rawAmount').val();

    $('#materList').append('<li id="raw"'+rawCount+'"><a href="#" onclick="deleteThis('+rawCount+
        ')">'+mater+':'+amount+'</a><input type="hidden" name="mater" value="'+mater+'"/>' +
        '<input type="hidden" name="rawsize" value="'+amount+'"/></li>')

    $('#searchBox').val("");
    $('#rawAmount').val("");
}

function plusProc() {

    $('#procCount').val(procCount)
    $('#process').append("<div class='input-group mb-3'>" +
        "        <label class='input-group-text' for='inputGroupFile01'>사 진</label>" +
        "        <input type='file' multiple name='procImg' class='form-control' id='procimg'>" +
        "    </div>" +
        "        <div class='mb-3'>" +
        "        <textarea class='form-control' name='procDetail' id='exampleFormControlTextarea1' placeholder='조리방법' rows='3'>"+

    "</textarea>" +
    "    </div>" +
    "</div>"
)
    ;
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
             $('#'+e).load(location.href+' #'+e)

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
            $('#'+e).load(location.href+' #'+e)
        }
    })

}
function deleteThis(e){
    $("#raw"+e).empty();
}

function makeReply(){
    let recipe= $('#recipe').val();
    let content = $('#content').val();
    console.log(content)
    $.ajax({
        url: '/recipe/makeReply',
        type: 'post',
        async: true,
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