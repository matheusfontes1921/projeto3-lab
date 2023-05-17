import * as React from 'react';
import {useEffect, useState} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import '../assets/listagem.css';
import axios from "axios";
import {useParams} from "react-router-dom";


export default function Listagem() {
    const { id } = useParams()
    const [vantagens, setVantagens] = useState([]);

    useEffect(() => {
        if(vantagens.length === 0) {
            if(window.location.pathname === '/listaVantgens') {
                document.getElementById("back").style.display = "none";

                axios.get(`http://localhost:8080/vantagens`)
                    .then((response) => {
                        console.log(response)
                        setVantagens(response.data)
                    })
                    .catch(response => {
                        console.log(response)
                    })
            } else {
                axios.get(`http://localhost:8080/empresas/vantagens/${id}`)
                    .then((response) => {
                        console.log(response)
                        setVantagens(response.data)
                    })
                    .catch(response => {
                        console.log(response)
                    })
            }
        }
    }, [vantagens]);

    function back() {
        window.location.pathname = document.location.pathname.split("vantagens")[0]
    }

    return (
        <div className="wrapper">
            <div id={"back"} onClick={back} style={{cursor: "pointer"}}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     className="bi bi-arrow-left" viewBox="0 0 16 16">
                    <path fillRule="evenodd"
                          d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                </svg>
            </div>
            <div className="title">
                <h1>Listagem de vantagens</h1>
            </div>

            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell align="right">Nome</TableCell>
                            <TableCell align="right">Descrição</TableCell>
                            <TableCell align="right">Custo</TableCell>
                            <TableCell align="right">Empresa</TableCell>
                            <TableCell align="right">Foto</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {vantagens.map((vantagem) => (
                            <TableRow
                                key={vantagem.id}
                                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">{vantagem.id}</TableCell>
                                <TableCell align="right">{vantagem.nome}</TableCell>
                                <TableCell align="right">{vantagem.descricao}</TableCell>
                                <TableCell align="right">{vantagem.custo}</TableCell>
                                <TableCell align="right">{vantagem.empresa.nome}</TableCell>
                                <TableCell align="right">{vantagem.foto}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
      </div>
    )
}