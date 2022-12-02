import React from 'react'
import Comment from './Comment'


// 1. 데이터 리스트 [ 서버 통신와 통신된 결과물 ]
const comments = [  // 댓글3개 객체를 저장하는 리스트 객체
    {   // 댓글1
        name : "이인제" ,
        comment : "안녕하세요, 소플입니다"
    },
    { // 댓글2
        name : "유재석" ,
        comment : "리액트 재미있어요"
    },
    { // 댓글3
         name : "강호동" ,
         comment : "저도 리액트 배워보고 싶습니다"
     }
];

function CommentList( props ){
    return(
        <div>
            { comments.map( (c) => {
                return(
                 <Comment name={ c.name } comment={ c.comment } />
                ) ;
            })}
        </div>
    );
}
export default CommentList

/*
    // map vs forEach
        // 리스트명.map( ( 반복변수명 ) => { 실행문 } )
        // 리스트명.forEach( ( 반복변수명 ) => { 실행문 } )
        // 1. 리스트객체 없이 직접 대입했을때.
//     return(
//         <div>
//              <Comment name={ "이인제" } comment={ "안녕하세요, 소플입니다" } />
//             <Comment name={ "유재석" } comment={ "리액트 재미있어요" } />
//             <Comment name={ "강호동" } comment={ "저도 리액트 배워보고 싶습니다" } />
//         </div>
//     );
*/