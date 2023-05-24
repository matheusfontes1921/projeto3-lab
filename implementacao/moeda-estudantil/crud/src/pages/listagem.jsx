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
import ShoppingBagIcon from '@mui/icons-material/ShoppingBag';


export default function Listagem() {
    const { id } = useParams()
    const [vantagens, setVantagens] = useState([]);
    const [saldo, setSaldo] = useState(0);
    const [isAluno, setIsAluno] = useState(false);
    const [idCompra, setIdCompra] = useState();
    const [compras, setCompras] = useState([])

    useEffect(() => {
        if(vantagens.length === 0) {
            if(window.location.pathname === `/aluno/${id}/listaVantgens`) {
                setIsAluno(true)

                axios.get(`http://localhost:8080/vantagens`)
                    .then((response) => {
                        setVantagens(response.data)
                    })
                    .catch(response => {
                        console.log(response)
                        setIdCompra(response.data.id)
                    })

                axios.get(`http://localhost:8080/alunos/saldo/${id}`, {headers: { "Content-Type": "application/json" }})
                    .then(res => {
                        setSaldo(res.data)
                    })
                    .catch(res => console.log(res))

            } else {
                axios.get(`http://localhost:8080/empresas/vantagens/${id}`)
                    .then((response) => {
                        setVantagens(response.data)
                    })
                    .catch(response => {
                        console.log(response)
                    })
            }
        }
    }, [vantagens]);

    function iniciar() {
        const idNumber = Number(id);

        const iniciarElement = document.getElementById('iniciar')

        if(iniciarElement.innerText === 'Iniciar Compras') {
            console.log('entrou')
            iniciarElement.innerText = "Confirmar"

            axios.post(`http://localhost:8080/compras/iniciar`, idNumber, {headers: {"Content-Type": "application/json"}})
                .then((res) => {
                    console.log(res)
                    setIdCompra(res.data)
                })
        } else {
            iniciarElement.innerText = "Iniciar Compras"

            axios.post(`http://localhost:8080/compras/finalizar/${idCompra}`)
                .then((res) => {
                    console.log(res)
                    iniciarElement.innerText = "Iniciar Compras"

                    compras.map((compras) => {
                        const vantagemElement = document.getElementById(compras);

                        vantagemElement.innerHTML = `comprar`
                        vantagemElement.style.color = '#00BB00'
                        vantagemElement.style.border = '1px solid #00BB00'
                    })
                })
        }
    }
    
    function comprar(vantagem) {
        const vantagemElement = document.getElementById(vantagem.id);
        if(idCompra === 0) {
            axios.post(`http://localhost:8080/compras/iniciar`, idNumber, {headers: { "Content-Type": "application/json" }})
                .then((res) => console.log(res))
        }

        if(vantagemElement.innerText === 'comprar') {
            const newSaldo = saldo - vantagem.custo

            if(newSaldo >= 0) {
                axios.post(`http://localhost:8080/compras/adicionarItem/${idCompra}`, vantagem.id, {headers: {"Content-Type": "application/json"}})
                    .then((res) => {
                        console.log(res)
                        vantagemElement.innerHTML = `cancelar`
                        vantagemElement.style.color = '#FF0000'
                        vantagemElement.style.border = '1px solid #FF0000'

                        setSaldo(newSaldo)
                        setCompras([...compras, vantagem.id])
                    })
            } else {
                const alert = document.getElementById("alert")
                alert.style.display = 'flex'
                alert.style.position = 'absolute'
                alert.style.top = '20px'
                alert.style.color = '#FF0000'
                alert.innerText = 'Saldo insuficiente'
            }

        } else {
            axios.delete(`http://localhost:8080/compras/apagarItem/${idCompra}/${vantagem.id}`)
                .then((res) => {
                    console.log(res)
                    vantagemElement.innerHTML = `comprar`
                    vantagemElement.style.color = '#00BB00'
                    vantagemElement.style.border = '1px solid #00BB00'

                    setSaldo(saldo + vantagem.custo)

                    const newCompras = [...compras]

                    newCompras.slice(newCompras.indexOf(vantagem.id, 1))
                    setCompras(newCompras)
                })

        }
    }

    function cancelar() {
        console.log(compras)

        compras.map((compraId) => {
            const vantagemElement = document.getElementById(compraId);

            axios.delete(`http://localhost:8080/compras/apagarItem/${idCompra}/${compraId}`)
                .then((res) => {
                    vantagemElement.innerHTML = `comprar`
                    vantagemElement.style.color = '#00BB00'
                    vantagemElement.style.border = '1px solid #00BB00'
                    const newCompras = [...compras]
                    newCompras.slice(newCompras.indexOf(compraId), 1)
                    setCompras(newCompras)

                    axios.post(`http://localhost:8080/compras/finalizar/${idCompra}`)
                        .then((res) => {
                            console.log("Finalizar", res)
                            document.getElementById("iniciar").innerText = "Iniciar Compras"
                        })
                        .catch((err) => {
                            document.getElementById("iniciar").innerText = "Iniciar Compras"
                        })
                })


            axios.get(`http://localhost:8080/alunos/saldo/${id}`, {headers: { "Content-Type": "application/json" }})
                .then(res => {
                    setSaldo(res.data)
                })
                .catch(res => console.log(res))
        })
    }

    function back() {
        if(isAluno) {
            window.location.pathname = document.location.pathname.split("listaVantgens")[0]
        } else {
            window.location.pathname = document.location.pathname.split("vantagens")[0]
        }
    }
    function close() {
       document.getElementById('alert').style.display = 'none'
    }

    return (
        <div className="wrapper">
            <div id={"back"} onClick={back} style={{cursor: "pointer", position: "absolute", left: "5px", top: "5px"}}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     className="bi bi-arrow-left" viewBox="0 0 16 16">
                    <path fillRule="evenodd"
                          d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                </svg>
            </div>
            <div id={"alert"} style={{display: "none", cursor: "pointer"}} onClick={close}></div>
            <div className="title">
                <h1>Listagem de vantagens</h1>
            </div>
            <div id={"saldo"}>Saldo: {saldo}</div>
            <div style={{display: 'flex'}}>
                <div
                    style={{margin: "5px", width: "100px", border: "1px solid #00BB00", color: "#00BB00", borderRadius: "6px", padding: "2px", fontSize: "12px", textAlign: "center", cursor: "pointer"}}
                    onClick={iniciar}
                    id={"iniciar"}
                >
                    Iniciar Compras
                </div>
                <div
                    style={{margin: "5px", width: "100px", border: "1px solid #FF0000", color: "#FF0000", borderRadius: "6px", padding: "2px", fontSize: "12px", textAlign: "center", cursor: "pointer"}}
                    onClick={cancelar}
                >
                    Cancelar compras
                </div>
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
                            {isAluno ? <TableCell align="right"><ShoppingBagIcon /></TableCell> : <></>}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {vantagens.length !== 0 ?
                            vantagens.map((vantagem) => (
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
                                    {isAluno ?
                                        <TableCell align="right">
                                            <div 
                                                style={{border: "1px solid #00BB00", color: "#00BB00", borderRadius: "6px", padding: "2px", fontSize: "12px", textAlign: "center", cursor: "pointer"}}
                                                onClick={() => comprar(vantagem)}
                                                id={vantagem.id}
                                            >
                                                comprar
                                            </div>
                                        </TableCell>
                                        :
                                        <></>
                                    }
                                </TableRow>
                            ))
                            :
                            <></>
                        }
                    </TableBody>
                </Table>
            </TableContainer>
      </div>
    )
}