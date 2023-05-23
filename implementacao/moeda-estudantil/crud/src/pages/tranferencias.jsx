import {useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";

export default function Transferencias() {
    const { id } = useParams();
    const [transferencias, setTransferencias] = useState([])
    const [saldo, setSaldo] = useState(0)

    useEffect(() => {
        const tipo = window.location.pathname.split('/')[1]

        if(transferencias.length === 0 ) {
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
                const back = document.getElementById("back")
                back.style.left = "5px";
                back.style.top = "5px";
                back.style.position = "absolute"

                axios.get(`http://localhost:8080/alunos/transferencias/${id}`)
                    .then((response) => {
                        setTransferencias(response.data)
                    })

                axios.get(`http://localhost:8080/alunos/saldo/${id}`, {headers: { "Content-Type": "application/json" }})
                    .then(res => {
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
        <div style={{display: "flex", flexDirection: "column", width: "100vw", height: "100vh", alignItems: "center", justifyContent: "flex-start"}}>
            <div id={"back"} onClick={back} style={{cursor: "pointer"}}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     className="bi bi-arrow-left" viewBox="0 0 16 16">
                    <path fillRule="evenodd"
                          d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                </svg>
            </div>
            <h1>Transferencias</h1>
            <h3>Saldo: {saldo}</h3>
            <div>
                <TableContainer component={Paper}>
                    <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell align="right">Valor</TableCell>
                                <TableCell align="right">Descrição</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {transferencias.length !== 0 ?
                                transferencias.map((transferencia) => (
                                    <TableRow
                                        key={transferencia.id}
                                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                    >
                                        <TableCell component="th" scope="row">{transferencia.id}</TableCell>
                                        <TableCell align="right">{transferencia.valor}</TableCell>
                                        <TableCell align="right">{transferencia.descricao}</TableCell>
                                    </TableRow>
                                ))
                                :
                                <></>
                            }
                        </TableBody>
                    </Table>
                </TableContainer>
            </div>
        </div>
    )

}