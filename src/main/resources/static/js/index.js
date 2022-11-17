alert('index')

getmember();

function getmember(){
    $.ajax({
        url : "/member/getloginMno",
        type : "get",
        success : function(re){
            alert(re)

            let headerbox = '';

            if(re == "0"){ //로그인안함
                headerbox += '<a href="/member/signup"><button type="button">회원가입</button></a>'+
                             '<a href="/member/login"><button type="button">로그인</button></a>'
            }else{// 로그인 했다.. [ 회원번호가 0 이 아니면 ]
                headerbox += '<a href="/member/findpw"><button type="button">비밀번호찾기</button></a>'+
                             '<a href="/member/delete"><button type="button">회원탈퇴</button></a>'+
                             '<a href="/member/update"><button type="button">비밀번호수정</button></a>'+
                             '<a href=""><button type="button" onclick="logoutMno()"> 로그아웃 </button></a>'
            }
            document.querySelector('.headerbox').innerHTML = headerbox;
        }
    })
}
//로그아웃
function logoutMno(){
    alert('logoutMno')

    $.ajax({
        url : "/member/logoutMno",
        type : "get",
        success : function(re){
            alert(re)
            if(re =="0"){  location.reload(); } //location.href = "/"; 인덱스호출
        }
    })
}
//회원목록
list();
function list(){
    $.ajax({
        url : "/member/list",
        type : "get",
        success : function(re){
            console.log(re)
            let html = '<tr> <th>번호</th> <th>이메일</th> <th>비밀번호</th> </tr>';
            console.log(html)
            list.forEach( m =>{
            html += '<tr> <th>'+m.mno+'</th> <th>'+m.memail+'</th> <th>'+m.mpassword+'</th> </tr>'
            })
            document.querySelector('.mtable').innerHTML = html;
            console.log(html)
        }
    })
}