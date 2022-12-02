import React from 'react';

function Book(props){

    //비동기 백엔드 통신
    fetch("http://localhost8080/member/list") //통신할 경로 url
        .then((response) => response.json()) //응답 자료형
        .then((data) => console.log(data)) //응답
    return (
        <div>
            <h1> 이책의 이름은 {props.name}입니다 </h1>
            <h2>이 책은 총 {props.numOfPage}페이지입니다 </h2>
        </div>
    )
}

//내보내기
export default Book;