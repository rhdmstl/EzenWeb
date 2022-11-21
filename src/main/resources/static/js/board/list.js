alert('글목록')

// ------- 전역변수 ------ //
let bcno = 0; // 선택된 카테고리 / 기본값 0 : 전체보기
boardlist();
function boardlist(){
    alert('dd')
    $.ajax({
      url: '/board/boardlist',
      type: 'get',
      data : {"bcno" : bcno},
      success: function(re){
        alert(re)
        console.log(re)
        let html = '<tr>  <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th> </tr>'
        console.log(html)
        re.forEach((b) =>{
            html += "<tr>"+
                    '<td>'+b.bno+'</td> <td><a onclick="getview('+b.bno+')">'+b.btitle+'<a/></td> <td>'+b.mno+'</td>'+
                    '</tr>';
        })
        document.querySelector('.btable').innerHTML = html
        console.log(html)
      }
    })
}
//2.상세조회
function getview(bno){
    alert(bno+'번 게시물보기')

    sessionStorage.setItem("bno" , bno); //1.클릭한 게시물번호 저장
    location.href = '/board/view' //2.페이지 전환
}

// 3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
        url : "/board/bcategorylist" ,
        type : "get" ,
        success : function(re){
            let html = '<button type="button" onclick="bcnochage(0)">전체보기</button>';
            re.forEach( c =>{
                console.log( c )
                html += '<button type="button" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>';
            })
            document.querySelector('.bcategorybox').innerHTML = html;
        }
    })
}
// 4. 카테고리 버튼을 클릭했을때 선택된 카테고리 번호 대입
function bcnochage( cno ){
    bcno = cno;
    console.log(bcno)
    alert( bcno );
    boardlist();
}