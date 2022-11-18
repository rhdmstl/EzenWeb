alert('view')

let bno = sessionStorage.getItem("bno");
console.log(bno);

    $.ajax({
        url: 'board/getboard',
        type: 'get',
        data: {"bno" : bno},
        success: function(re){
            alert(re);

        }
    })