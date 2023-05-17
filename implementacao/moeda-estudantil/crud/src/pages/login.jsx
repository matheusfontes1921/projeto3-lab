import React, {useState} from 'react';
import logo from '../assets/coins.png';
import '../assets/login.css';
import axios from "axios";
import {useNavigate} from "react-router-dom";

const initialUserState = {
    username: '',
    password: ''
}

function Login() {
    const [user, setUser] = useState(initialUserState);
    const navigate = useNavigate();
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

    function handleSubmit() {
        if(user.username === '' || user.password === '')
            console.log('vazio', user.username, user.password)
        else {
            event.preventDefault();
            const header = {'Content-Type': 'application/json'}
            const data = {
                email: user.username,
                password: user.password
            }

            axios.post("http://localhost:8080/auth/login", JSON.stringify(data), {headers: header})
                .then(response => {
                    if (response.status === 200) {
                        if(response.data.hasOwnProperty('curso')) {
                            navigate(`aluno/transferencias/${response.data.id}`)
                        }else if(response.data.hasOwnProperty('departamento')) {
                            navigate(`professor/${response.data.id}`)
                        } else {
                            navigate(`empresa/${response.data.id}`)
                        }
                    }
                })
                .catch(response => console.log("catch", response))
        }
    }

    return (
    <>
       <form className="wrapper" onSubmit={handleSubmit}>
            <div className="login">
                <h1>Sistema de moeda estudantil</h1>
            </div>
            <div className="imgs">
                <img src={logo} alt="logo-img"></img>
                <p>Bem-vindo ao sistema de moedas da PUC Minas</p>
            </div>
            <div className="inputs">
                <label>Usuário: </label>
                <input
                    type="text"
                    className="user-input"
                    placeholder="Digite seu nome de usuário"
                    onChange={(event) => setUser({...user, username: event.target.value})}
                    required/>
            </div>
            <div className="password">
            <label>Senha: </label>
                <input
                    type="password"
                    className="password-input"
                    placeholder="Digite sua senha"
                    onChange={(event) => setUser({...user, password: event.target.value})}
                    required/>
                <p>Esqueceu sua senha?</p>
                <button type={"submit"}>Login</button>
            </div>
       </form>
   </>
  )
}
export default Login;