import React from "react";
import Header from "./Header";
import Footer from "./Footer";
import Sigup from  "./member/Signup";
import Style from "../css/header.css";
//라우터 설치 npm i react-router-dom
    //import {컴포넌트명} from  'react-router-dom';
import { BrowserRouter , Routes , Route , Link } from  'react-router-dom';
import Signup from "./member/Signup";

export default function Index(props){
    return (
        <div>
            <BrowserRouter>
                <Header/>
                    <h3>메인페이지</h3>
                <Footer/>
                <Routes>
                    <Route path="/"></Route>
                    //컴포넌트를 부르는 경로
                    <Route path="/member/signup" element={<Signup/>}></Route>
                </Routes>
            </BrowserRouter>
        </div>);
}

