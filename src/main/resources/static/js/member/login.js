/*
alert('dd')

function getmember(){
    alert('getmember')

    let info = {
         memail : document.querySelector('.memail').value,
         mpassword : document.querySelector('.mpassword').value
    }

    $.ajax({
        url: "/member/getmember",
        type: "POST",
        data : JSON.stringify(info),
        contentType: "application/json;",
        success: function(re){
            alert(re+'성공')
            location.href = '/';
        }
    })
}*/
