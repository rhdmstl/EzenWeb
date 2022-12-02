/* function App(props){
    alert(props)
    return(
        <Profile
            name="소플"
            introduction="안녕하게요 소플입니다"
            viewCount={1500}
        />
    );//return end
} // App end */

function App(props){
    alert(props)
    return(
        <Layout
            width={2560}
            height={1440}
            header={<Header title="소플의 블로그"/>}
            footer={<Footer />}
        />
    );//return end
} // App end
/*
    재활용성이 좋다
*/