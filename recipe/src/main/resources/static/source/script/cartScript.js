function deleteIt(e){
    $.ajax({
        url: "http://localhost:8080/myPage/deleteCart",
        async: true,
        type: 'post',
        data: {cartId: e},
        success(){
            $('#cartTable').load(location.href+' #cartTable')
        }
    })
}

function changeIt(d){
    let count = $('#cartquan'+d).val();
    $.ajax({
        url: "http://localhost:8080/myPage/changeCart",
        type: 'post',
        data:{cartId: d, quantity: count},
        dataType:'json',
        success(){
            $('#cartTable').load(location.href+' #cartTable')
        }
    })
}

