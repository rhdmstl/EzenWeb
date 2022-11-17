alert('dd')

function setupdate(){

    let mpassword =
    document.querySelector('.mpassword').value

    $.ajax({
        url : "/member/setupdate" ,
        type : "put" ,
        data : { "mpassword" : mpassword } ,
        success : function(re) { alert(re) }

    })
}