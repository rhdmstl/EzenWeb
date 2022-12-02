import React from "react";
import Style from "../css/header.css";
import logo from '../img/logo.jpg';
import { HashRouter, BrowserRouter, Routes, Route, Link,  Router } from "react-router-dom";

export default function Header(props){
    return (
        <div>
            <img className="logo" src={logo} />
            <h3 className={"header_name"}>헤더</h3>
            <ul>
                <li> <Link to="/" > Home                   </Link> </li>
                <li> <Link to="/member/signup" > 회원가입   </Link> </li>
                <li> <a href="/member/logout"> 로그아웃     </a> </li>
            </ul>
        </div>
    );
}