alert('dd')
boardlist();
function boardlist(){
 console.log('eeee')
    $.ajax({
      url: '/board/boardlist',
      type: 'get',
      success: function(re){
        console.log(re)
        let html = '<tr>  <th> 번호 </th> <th> 제목 </th> <th> 작성자 </th> </tr>'
        re.forEach((b) =>{
            html += "<tr>"+
                    '<td>'+b.bno+'</td> <td><a onclick="getview('+b.bno+')">'+b.btitle+'<a/></td> <td>'+b.mno+'</td>'+
                    '</tr>';
        })
        document.querySelector('.btable').innerHTML = html
      }
    })
}
//상세조회
function getview(bno){
    alert(bno+'게시물보기')

    sessionStorage.setItem("bno" , bno); //1.클릭한 게시물번호 저장
    location.href = '/board/view' //2.페이지 전환
}