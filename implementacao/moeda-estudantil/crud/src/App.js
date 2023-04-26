import './App.css';
import {useState} from "react";
import axios from "axios";
import {contentType} from "yarn/lib/cli";
import {Route, Routes} from "react-router-dom";
import Home from "./pages/home";

function App() {
    return (
        <Routes>
            <Route index element={<Home/>}/>
            <Route path={"/aluno"} element={<Home/>}/>
            <Route path={"/empresa"} element={<Home/>}/>
        </Routes>
    )
}

export default App;
