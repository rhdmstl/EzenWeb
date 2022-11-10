alert('dd')
function getMapping1(){
        $.ajax({
            url : "/api/v1/get-api/hello",
            success : re =>{
                alert(re)
            }
        })
    }
    function getMapping2(){
        $.ajax({
            url : "/api/v1/get-api/name",
            success : re =>{
                alert(re)
            }
        })
    }

    function getMapping3(){
        $.ajax({
            url : "/api/v1/get-api/variable1/dd",
            success : re => {alert(re)}
        })
    }
    function getMapping4(){
        $.ajax({
            url : "/api/v1/get-api/variable2/1234",
            success : re => {alert(re)}
        })
    }
    function getMapping5(){
        $.ajax({
            url : "/api/v1/get-api/variable3?variable=aa",
            success : re => {alert(re)}
        })
    }
    function getMapping6(){
        $.ajax({
            url : "/api/v1/get-api/requst1?name=짱&email=naver.com&organization=good",
            success : re => {alert(re)}
        })
    }
    function getMapping7(){
        $.ajax({
            url : "/api/v1/get-api/requst2?key1=aa&key2=aa&key3=aa",
            success : re => {alert(re)}
        })
    }
    function getMapping8(){
            $.ajax({
                url : "/api/v1/get-api/requst3?name=고은시&email=qweqwe@qwe&organization=zzzz",
                success : re => {alert(re)}
            })
        }
//--11/10 postmapping--------------------------------------------------------------
    function postmapping1(){
        let member = {
                name : "유재석",
                email : "qweqwe@qwe",
                organization : "post"
            }
        $.ajax({
            url : "/api/v1/post-api/domain",
            type : "POST",
            data : JSON.stringify(member),
            success : re => {alert(re)}
        })
    }
    function postmapping2(){
        let member = {
            name : "유재석",
            email : "qweqwe@qwe",
            organization : "post"
        }
        $.ajax({
            url : "/api/v1/post-api/domain",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(member),
            success : re => {alert(re)}
        })
    }
    function postmapping3(){
        let member = {
            name : "유재석",
            email : "qweqwe@qwe",
            organization : "post"
        }
        $.ajax({
            url : "/api/v1/post-api/domain",
            type : "POST",
            contentType : "application/json",
            data : JSON.stringify(member),
            success : re => {alert(re)}
        })
    }
//--11/10 putmapping--------------------------------------------------------------
    function putmapping1(){
        let member = {
                name : "유재석",
                email : "qweqwe@qwe",
                organization : "put"
            }
        $.ajax({
        url : "/api/v1/put-api/member",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : re => {alert(re)}
        })
    }
     function putmapping2(){
        let member = {
                name : "유재석",
                email : "qweqwe@qwe",
                organization : "put2"
            }
        $.ajax({
        url : "/api/v1/put-api/member1",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : re => {
            console.log(re)
            alert(re)}
        })
    }
    function putmapping3(){
        let member = {
                name : "유재석",
                email : "qweqwe@qwe",
                organization : "put3"
            }
        $.ajax({
        url : "/api/v1/put-api/member2",
        type : "PUT",
        data : JSON.stringify(member),
        contentType : "application/json",
        success : re => {
            console.log(re)
            console.log(re.name)
            alert(re)
           // let json = JSON.parse(re)
            //console.log(re) DTO로 들어와서 파싱할 필요가 없음
            }
        })
    }
//--11/10 deletemapping--------------------------------------------------------------
    function deletemapping1(){

        $.ajax({
        url : "/api/v1/delete-api/3",
        type : "Delete",
        success : re => {
            console.log(re)
            console.log(re.name)
            alert(re)
            }
        })
    }
    function deletemapping2(){

        $.ajax({
        url : "/api/v1/delete-api/requst1?variable=하하",
        type : "Delete",
        //data : {바디로 보내면 제이슨으로 파싱해줘야함}
        success : re => {
            console.log(re)
            console.log(re.name)
            alert(re)
            }
        })
    }