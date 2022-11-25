alert('수정')

let bno = sessionStorage.getItem("bno");    // 1. 세션스토리지 호출

getboard()
function getboard(){
    $.ajax({
        url : "/board/getboard" ,
        type : "get" ,
        data : { "bno" : bno} ,
        success : function(re){ console.log( re ) }
    })
}

function upboard(){
    alert('dd')
     let data = {
           btitle : document.querySelector('.btitle').value ,
           bcontent : document.querySelector('.bcontent').value,
           bfile : document.querySelector('.bfile').value,
           bno : bno
       }

    $.ajax({
        url : "/member/upboard" ,
        type : "put" ,
        data : JSON.stringify(data) ,
        contentType : "application/json",
        success : function(re) {
            if(re == true){
                alert('수정성공')
                location.href = "/board/view"
            }
            else{
                alert('수정실패')
            }
        }
    })
}