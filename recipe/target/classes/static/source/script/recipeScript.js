var rawMaterList = [];
var procCount = 1;
let rawMater = Object();
function addRaws(){

    rawMater = new Object();

    rawMater.mater = $('#rawName').val();
    rawMater.amount = $('#rawAmount').val();

    rawMaterList.push(rawMater);


    $('#rawName').val("");
    $('#rawAmount').val("");
    console.log(rawMaterList);


}

function plusProc() {
   procCount += 1
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