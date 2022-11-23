alert('방명록')
//4.카테고리 기본값 전역변수
let bgcno = 4;
guestlist();
function setvisit(){ //1.게시물등록

    let data = {
        bgtitle : document.querySelector('.bgtitle').value ,
        bgcontent : document.querySelector('.bgcontent').value,
        bgcno : bgcno
    }
    $.ajax({
        url : "/board/setvisit",
        type : 'POST',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re == true){
                alert(re+'등록완료')
                location.reload;
            }else{
                alert(re+'등록실패')
                location.reload;
            }
        }
    })
}

//2. 게시물 카테고리 등록
function setvisitcategory(){
    let data = { bgcname : document.querySelector('.bgcname').value }
    $.ajax({
        url : "/board/setvisitcategory",
        type : 'POST',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re == true){
                alert(re+'등록 성공')
            }else{
                alert('등록 실패')
            }
        }
    })
}

//3.모든 카테고리 출력
visitcategorylist();
function visitcategorylist(){
    $.ajax({
      url : "/board/visitcategorylist"  ,
      type : 'GET',
      success : function(re) {
         let html = '<button type="button" onclick="gcnochange(4)">전체보기</button>';
        re.forEach(c => {
            html += '<button type="button" onclick="gcnochange('+c.bgcno+')">'+c.bgcname+'</button>'
        })
        document.querySelector('.gcategorybox').innerHTML = html
       }
    })
}
// 4. 카테고리 버튼을 클릭했을때 선택된 카테고리 번호 대입
function gcnochange( cno ){
    bgcno = cno;
    guestlist();
    console.log(bgcno)
    alert( bgcno );
    visitcategorylist();
}
//게시물 출력
function guestlist(){
    console.log('dd')
    $.ajax({
      url: '/board/guestlist',
      type: 'get',
      data : {"bgcno" : bgcno},
      success: function(re){
        alert(re)
        console.log(re)

        let html = '<tr>  <th> 번호 </th> <th> 제목 </th> <th> 내용 </th> </tr>'
        console.log(html)
        re.forEach((b) =>{
            html += "<tr>"+
                    '<td>'+b.bgno+'</td> <td>'+b.bgtitle+'</td> <td>'+b.bgcontent+'</td>'+
                    '</tr>';
        })
        document.querySelector('.gtable').innerHTML = html
        console.log(html)
      }
    })
}
function upboard(){
    alert('수정')
    let data = {
            btitle : document.querySelector('.bgtitle').value ,
            bcontent : document.querySelector('.bgcontent').value,
            bgno = bgno
        }

    $.ajax({
        url : "/board/guestput",
        type : 'put',
        data : JSON.stringify(data),
        contentType : "application/json",
        success : function(re){
            if(re == true){
                alert(re+'수정완료')
                location.reload; 
            }else{
                alert('등록실패')
                location.reload;
            }
        }
    })
}