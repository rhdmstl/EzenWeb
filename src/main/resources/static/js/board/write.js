alert('글쓰기')
//4.카테고리 기본값 전역변수
let bcno = 2;

/*function setboard(){ //1.게시물등록
    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bcno : bcno
    }

    $.ajax({
        url : "/board/setboard",
        type : 'POST',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re == true){
                alert(re+'등록완료')
                location.href = '/'
            }else{
                alert(re+'등록실패')
                location.reload;
            }
        }
    })
}*/
function setboard(){ //1.게시물등록 (첨부파일 등록을 위해 수정)

    let boardform = document.querySelector('.boardform')
    let formdata = new FormData(boardform)
    formdata.set("bcno",bcno) ///폼에 카테고리 번호 추가
                //"이름",data
console.log(formdata)
    $.ajax({
        url : "/board/setboard",
        type : 'POST',
        data : formdata,
        //첨부파일을 위한 컨테츠타입,프로세스
        contentType : false,
        processData : false,
        success : function(re){
            if(re == true){
                alert(re+'등록완료')
                location.href = '/'
            }else{
                alert(re+'등록실패')
                location.reload;
            }
        }
    })
}

//2. 게시물 카테고리 등록
function setbcategory(){
    let data = { bcname : document.querySelector('.bcname').value }
    $.ajax({
        url : "/board/setbcategory",
        type : 'POST',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re == true){
                alert(re+'등록 성공')
                bcategorylist()
            }else{
                alert('등록 실패')
            }
        }
    })
}

//3.모든 카테고리 출력
bcategorylist();
function bcategorylist(){
    $.ajax({
      url : "/board/bcategorylist"  ,
      type : 'GET',
      success : function(re) {
        let html = '';
        re.forEach(c => {
            html += '<button type="button" onclick="bcnochange('+c.bcno+')">'+c.bcname+'</button>'
        })
        document.querySelector('.bcategorybox').innerHTML = html
        cbtn = document.querySelectorAll('.cbtn') //위에서 생성된 카테고리 버튼들 호출
        }
    })
}
//4.카테고리를 선택했을떄 선택된 카테고리번호 변경
function bcnochange(cno){
    bcno = cno
    console.log(bcno)
    alert(bcno+'를 선택')
}
