import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

export default function Transferencias() {
    const { id } = useParams();
    const [transferencias, setTransferencias] = useState([])

    useEffect(() => {
        const tipo = window.location.pathname.split('/')[1]

        if(transferencias.length === 0) {
            if (tipo === 'professor') {
                axios.get(`http://localhost:8080/professores/transferencias/${id}`)
                    .then((response) => {
                        setTransferencias(response.data)
                    })
                    .catch(response => {console.log(response)})
            } else if(tipo === 'aluno') {
                axios.get(`http://localhost:8080/alunos/transferencias/${id}`)
                    .then((response) => {
                        setTransferencias(response.data)
                    })
            }

        }
    }, [transferencias]);

    return(
        <div style={{display: "flex", flexDirection: "column", width: "98vw", height: "90vh", alignItems: "center", justifyContent: "flex-start"}}>
            <h1>Transferencias</h1>
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
                        </li>
                    )
                })}
            </ul>
        </div>
    )

}