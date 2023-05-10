//import { useState } from 'react'
//import reactLogo from './assets/react.svg'
//import viteLogo from '/vite.svg'
import React from 'react';
import logo from './assets/coins.png';
import { useEffect } from 'react';
import './assets/login.css';

function Login() {
  //  let imgs = useRef(null);
  //   let headers = useRef(null);
  //   let logos = useRef(null);
  //   let form = useRef(null);

  //   useEffect(()=> {
  //       TweenMax.to(imgs,1,{delay:0.4,opacity:1, ease: 'easeOut'})
  //       TweenMax.to(logos,2,{delay:1.5,opacity:1,ease:'easeOut'})
  //       TweenMax.to(headers,2,{delay:2,opacity:1, ease:'easeOut'})
  //       TweenMax.to(form,2,{delay:2.5,opacity:1, ease:'easeOut'})
  //   })

  return (
    <>
   <div className="wrapper">
    <div className="login">
      <h1>Sistema de moeda estudantil</h1>
    </div>
    <div className="imgs">
      <img src={logo} alt="logo-img"></img>
      <p>Bem-vindo ao sistema de moedas da PUC Minas</p>
    </div>
    <div className="inputs">
      <label>Usuário: </label>
      <input type="text" className="user-input" placeholder="Digite seu nome de usuário"/> 
    </div>
    <div className="password">
    <label>Senha: </label>
      <input type="password" className="password-input" placeholder="Digite sua senha"/>
      <p>Esqueceu sua senha?</p>
      <button>Login</button>
    </div>
   </div>
   </>
  )
}
export default Login;