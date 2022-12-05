import React from "react";
import Styles from './Notification.css';

//2. 클래스를 이용한 컴포넌트 생성[ React.Component 상속 ]
class Notification extends React.Component{
    //1.생성자
    constructor(props) {
        super(props);   //슈퍼클래스 생성자 호출
        this.state = { }    //상태관리 변수
    }

    //*생명주기 함수실행 순서확인
    componentDidMount() { console.log("`${this.props.id}`출생함수") }

    componentDidUpdate(prevProps, prevState, snapshot) { console.log("`${this.props.id}`업뎃함수") }

    componentWillUnmount() { console.log("`${this.props.id}`사망함수") }

    render() {  //2.랜더링 함수
        return(
            <div className="wrapper">
                <span className="messageText">
                    {this.props.message}
                </span>
            </div>
        )
    }
}

//3.컴포넌트 내보내기
export default Notification

//this : 현재 클래스 필드 호출 [해당 메소드 내 필드와 클래스 필드 구분 ]
//props : 컴포넌트 속성 : 매개변수 [ 객체 ]