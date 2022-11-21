alert('update')

// 1. list.js 에서 클릭된 게시물번호 호출
let bno = sessionStorage.getItem("bno");// 세션스토리지 호출

//2.
getboard();
function getboard(){
$.ajax({
    url : "/board/getboard",
    type : "get",
    data : {"bno" : bno},
    success : function(re) {console.log(re)}
    })
}

function upboard(){
    alert('수정')
    let data = {
            btitle : document.querySelector('.btitle').value ,
            bcontent : document.querySelector('.bcontent').value,
            bfile : document.querySelector('.bfile').value,
            bno = bno
        }

    $.ajax({
        url : "/board/upboard",
        type : 'put',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re == true){
                alert(re+'수정완료')
                location.href = '/board/view'
            }else{
                alert('등록실패')
                location.reload;
            }
        }
    })
}