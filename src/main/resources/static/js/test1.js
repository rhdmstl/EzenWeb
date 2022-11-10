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
