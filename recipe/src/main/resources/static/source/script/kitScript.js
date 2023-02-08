
$(document).ready(function(){
    $('#recipebtn').click(function() {
        $.ajax({
            type: "post",
            async: true,
            dataType: 'json',
            url: "http://localhost:8080/admin/famousRecipe",
            success: function(data) {
                var result = data;
                console.log(data);

                $.each(result, function(index, item){
                    let title = "'"+item.recipeTitle+"'";
                    let id = "'"+item.recipeId+"'";
                    let list = '<li><a href="#" onclick="select('+title+')">'+item.recipeTitle+'</a></li>';
                    let table = '<tr><th>'+item.recipeTitle+'</th><td>'+item.likeCount+'</td><td>' +
                        '<a href="#" onclick="moveRecipe('+id+')">페이지보기</a></td><td><a href="#" onclick="select(' +
                        title+')">추가하기</a></td></tr>'

                    console.log(table);
                    $('#recipeList').append(table);
                    table.trim()
                })
            }
        });
        $('#recipeModal').modal('show');
    });
});

function select(e){
    $("#recipeTitle").val(e);
}

function moveRecipe(e){
    window.open('http://localhost:8080/common/getRecipe?recipeId='+e, "_blank", 'width=800px,height=450px,scrollbars=yes');
}

