alert('button')

//1.호출
const domContainer = document.querySelector('#root')
console.log(domContainer)
//2.랜더링 [render(이벤트 , 위치)
ReactDOM.render(React.createElement(MyButton), domContainer)
//3.버튼 생성함수
function MyButton(props){

    const[ isClicked , setIsClicked ] = React.useState(false);

    return React.createElement(
        'button',
        {onClick: () => setIsClicked(true)}, //옵션이벤트
        isClicked ? 'Clicked' : 'Click here!' // html작성
   )
}