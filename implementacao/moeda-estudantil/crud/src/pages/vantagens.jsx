import React, {useState} from "react";
import '../assets/vantagens.css';


import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import axios from "axios";
import {useParams} from "react-router-dom";

const vantagemInicial = {
    idVantagem: '',
    name: '',
    description: '',
    foto: '',
    custo: ''
}


export default function Vantagens() {
    const { id } = useParams()
    const [vantagem, setVantagem] = useState(vantagemInicial)

    function cadastrar() {
        const { name, description, foto, custo } = vantagem

        if(custo > 0) {
            axios.post(`http://localhost:8080/empresas/${id}/${name}/${description}/${foto}/${custo}`,{}, {headers: { "Content-Type": "application/json" }})
                .then(res => {
                    if (res.status === 201) {
                        setVantagem(vantagemInicial);
                        const alert = document.getElementById("alert");
                        alert.innerHTML = "Created " + res.status;
                        alert.style.color = "green";
                        alert.style.display = "inline-block"
                    } else {
                        const alert = document.getElementById("alert");
                        alert.innerHTML = "Error" + res.status;
                        alert.style.color = "red";
                        alert.style.display = "inline-block"
                    }
                })
        } else {
            const alert = document.getElementById("alert");
            alert.innerHTML = "Valor invalido";
            alert.style.color = "red";
            alert.style.display = "inline-block"
        }
    }

    function clearAlert() {
        const alert = document.getElementById("alert");
        alert.style.display = "none"
    }

    function back() {
        const path = document.location.pathname.split("crud")[0];
        window.location.pathname = path
    }

    return(
        <>
            <div className="news-container">
                <div onClick={back} style={{cursor: "pointer"}}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         className="bi bi-arrow-left" viewBox="0 0 16 16">
                        <path fillRule="evenodd"
                              d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                    </svg>
                </div>

                <div id={"alert"} onClick={clearAlert} style={{display: "none", cursor: "pointer"}}></div>
                <h1 className="title" >Cadastrar vantagens</h1>
                <div className="content-container">
                    <div className="content-inputs">
                        <h2>Cadastro</h2>
                        <TextField
                            id="nome"
                            label="Nome"
                            variant="outlined"
                            value={vantagem.name}
                            multiline
                            onChange={(event) =>
                                setVantagem({...vantagem, name: event.target.value})
                            }
                        />
                        <TextField
                            id="descricao"
                            label="Descrição"
                            variant="outlined"
                            value={vantagem.description}
                            multiline
                            onChange={(event) =>
                                setVantagem({...vantagem, description: event.target.value})
                            }
                        />
                        <TextField
                            id="custo"
                            label="Custo"
                            variant="outlined"
                            value={vantagem.custo}
                            multiline
                            onChange={(event) =>
                                setVantagem({...vantagem, custo: event.target.value})
                            }
                        />
                        <TextField
                            id="foto"
                            label="Foto"
                            variant="outlined"
                            value={vantagem.foto}
                            inputProps={{maxLenght: undefined }}
                            multiline
                            onChange={(event) =>
                                setVantagem({...vantagem, foto: event.target.value})
                            }
                        />
                        <Button variant="contained" onClick={cadastrar} >CADASTRAR</Button>
                    </div>
                </div>

            </div>
        </>
    )
}

