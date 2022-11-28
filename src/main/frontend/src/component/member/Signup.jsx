
import React from 'react';
import styles from './Signup.css';
import axios from 'axios'; //   npm install axios 설치 했을경우만 가능

function Signup( props ){

    const setmember = () => {  // 1. setmember 이벤트 함수 정의 [ 화살표함수 정의 ]

        let info = {    // 2. 입력받은 값 가져오기
            memail : document.querySelector('.memail').value ,
            mpassword : document.querySelector('.mpassword').value ,
            mphone : document.querySelector('.mphone').value
        }

        //  @CrossOrigin(origins = "http://localhost:3000")
      axios           // 3. axios 비동기통신 이용한 서버[spring] 통신
            .post( "http://localhost:8080/member/setmember" ,  info ) // 요청
            .then( res => { alert( res.data) } )                   // 응답

    }
    // 2. 인증코드 요청 함수
    const getauth = () => {   alert(" 클릭 이벤트 ")  }
    // 3. 타이머 함수
    const setimer = () => {   alert(" 클릭 이벤트 ")  }
    // 4. 인증 버튼 확인 함수
    const authcode = () => {   alert(" 클릭 이벤트 ")  }
    return(
            <div>
                <h3> 회원가입 </h3>
                <div>
                    이메일 : <input type="text" className="memail" />
                    <button type="button" onClick={ getauth } className="getauthbtn"> 인증코드받기 </button><br/>
                    <div className="authbox">
                        <input type="text" className="authinput" />
                        <button type="button" className="authbtn" onClick={ authcode } > 인증 </button>
                        <span className="timerbox"></span>
                    </div>
                </div>
                핸드폰 : <input type="text" className="mphone" /><br/>
                비밀번호 : <input type="text" className="mpassword" /><br/>
                <button type="button" onClick={ setmember } > 가입하기 </button>
            </div>
    );
}
export default Signup;