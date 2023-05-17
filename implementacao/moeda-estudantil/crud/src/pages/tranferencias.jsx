import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";

export default function Transferencias() {
    const { id } = useParams();
    const [transferencias, setTransferencias] = useState([])
    const [saldo, setSaldo] = useState(0)

    useEffect(() => {
        const tipo = window.location.pathname.split('/')[1]

        if(transferencias.length === 0) {
            if (tipo === 'professor') {
                axios.get(`http://localhost:8080/professores/transferencias/${id}`)
                    .then((response) => {
                        setTransferencias(response.data)
                    })
                    .catch(response => {console.log(response)})
                axios.get(`http://localhost:8080/professores/saldo/${id}`, {headers: { "Content-Type": "application/json" }})
                    .then(res => {
                        console.log(res)
                        setSaldo(res.data)
                    })
                    .catch(res => console.log(res))
            } else if(tipo === 'aluno') {
                document.getElementById("back").style.display = "none";

                axios.get(`http://localhost:8080/alunos/transferencias/${id}`)
                    .then((response) => {
                        setTransferencias(response.data)
                    })

                axios.get(`http://localhost:8080/alunos/saldo/${id}`, {headers: { "Content-Type": "application/json" }})
                    .then(res => {
                        console.log(res)
                        setSaldo(res.data)
                    })
                    .catch(res => console.log(res))
            }

        }
    }, [transferencias]);

    function back() {
        window.location.pathname = document.location.pathname.split("transferencias")[0]
    }

    return(
        <div style={{display: "flex", flexDirection: "column", width: "98vw", height: "90vh", alignItems: "center", justifyContent: "flex-start"}}>
            <div id={"back"} onClick={back} style={{cursor: "pointer"}}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     className="bi bi-arrow-left" viewBox="0 0 16 16">
                    <path fillRule="evenodd"
                          d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                </svg>
            </div>
            <h1>Transferencias</h1>
            <h3>Saldo: {saldo}</h3>
            <ul style={{listStyle: 'none'}}>
                {transferencias.map(transferencia => {
                    console.log("t", transferencia)

                    return (
                        <li
                            key={transferencia.id}
                            style={{
                                display: "flex",
                                justifyContent: "space-evenly",
                                width: "50vw",
                                border: "1px solid"
                            }}
                        >
                            <span>id da transferencia: {transferencia.id}</span>
                            <span>valor: {transferencia.valor}</span>
                            <span>descricao: {transferencia.descricao}</span>
                        </li>
                    )
                })}
            </ul>
        </div>
    )

}