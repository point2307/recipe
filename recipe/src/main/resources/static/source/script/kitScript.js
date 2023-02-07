$(document).ready(function(){
    $('#searchBtn').click(function() {
        $('#recipeModal').modal('show');
    });
});



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
                    let title = "'"+item.recipeTitle+"'"
                    let list = '<li><a href="#" onclick="select('+title+')">'+item.recipeTitle+'</a></li>'
                    console.log(list)
                    $('#recipeList').append(list);
                    list.trim()
                })
            }
        });
    });
});

function select(e){
    $("#recipeTitle").val(e);
}
