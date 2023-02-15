var itemPrice = 0;
var itemCount = 1;
$(document).ready(function(){
    $('#searchbtn').click(function() {
        $.ajax({
            type: "post",
            async: true,
            dataType: 'json',
            url: "http://localhost:8080/admin/selectKit",
            success: function(data) {
                var result = data;
                console.log(data);
                $('#kitList').empty();
                $.each(result, function(index, item){
                    let title = item.kitName;
                    let price = item.price;
                    let kitId = item.kitId
                    let table = '<tr><th><label><input type="checkbox" name="kit" value="'+title+'"></label></th>'+
                        '<td>'+item.kitName+'</td><td>'+price+'</td><td><a href="#" onclick="kitPage('+kitId+')">이동하기</a></td></tr>'
                    console.log(table);

                    $('#kitList').append(table);
                    table.trim()
                })
            }
        });
        $('#kitModal').modal('show');
    });
});

function kitPage(e){
    window.open('http://localhost:8080/common/getKit?kitId='+e, "_blank", 'width=800px,height=450px,scrollbars=yes');
}

function kitSelect(){
    let kitleng = $('input:checkbox[name=kit]:checked').length;
    console.log(kitleng)
    for(var i=0; i<kitleng; i++){
        $("#checkedKit").append('<li id="kitli'+i+'"><a href="#" onclick="deleteThis('+i+')">'+$("input:checkbox[name=kit]:checked").eq(i).val()+'</a>'+
            '<input type="hidden" name="kit" value="'+$("input:checkbox[name=kit]:checked").eq(i).val()+'"/></li>');
    }
}

function deleteThis(e){
    $("#kitli"+e).empty();
}

ga= () =>{

    let pri = $('#select-kit option:selected').text()
    console.log(pri)
    let int = pri.substring(pri.indexOf(':')+2, pri.indexOf('원'))
    console.log(int)
    addItems(int)
}
function addItems(e){
    itemPrice = e;
    let result = itemCount * itemPrice
    $('#selectPrice').text('총 가격 : '+result+'원')
}
function addCount(){
    itemCount = $('#select-count').val();
    let result = itemPrice * itemCount
    $('#selectPrice').text('총 가격 : '+result+'원')
}