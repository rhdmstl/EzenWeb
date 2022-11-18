alert('dd')
function setboard(){
    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value
    }

    $.ajax({
        url : "/board/setboard",
        type : 'POST',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            alert(re+'등록완료')
            if(re == true){
                location.href = '/'
            }else{
                alert('등록실패')
                location.reload;
            }
        }
    })
}