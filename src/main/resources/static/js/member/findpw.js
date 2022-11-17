alert('dd')

function getpassword(){
    alert('getpassword')

    let memail = document.querySelector('.memail').value;

        $.ajax({
            url: "/member/getpassword",
            type: "GET",
            data : {"memail" : memail},
            success: function(re){
                alert(re)
            }
        })
}