import React, {useEffect, useState} from "react";
import '../assets/vantagens.css';


import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { DataGrid } from "@mui/x-data-grid";
import {Box} from "@mui/material";


export default function Vantagens() {

    return(
        <>
                
                <div className="news-container">
                <h1 className="title">Cadastrar vantagens</h1>
                    <div className="content-container">
                        <div className="content-inputs">
                        <TextField
                                id="id"
                                label="ID"
                                variant="outlined"
                                multiline
                                 />
                           <TextField
                                id="nome"
                                label="Nome"
                                variant="outlined"
                                multiline
                                 />
                            <TextField
                                id="descricao"
                                label="Descrição"
                                variant="outlined"
                                multiline
                                 />
                                 <TextField
                                id="custo"
                                label="Custo"
                                variant="outlined"
                                multiline
                                 />
                                 <TextField
                                id="empresa"
                                label="Empresa"
                                variant="outlined"
                                multiline
                                 />
                                  <TextField
                                id="foto"
                                label="Foto"
                                variant="outlined"
                                multiline
                                 />
                        </div>
                        <div className="content-actions">
                            <Button variant="contained"  >CADASTRAR </Button>
                            <Button variant="contained" >EDITAR </Button>
                            <div style={{marginTop: '3rem'}}>
                                <Button variant="text" color="error" >REMOVER </Button>
                            </div>
                        </div>
                    </div>

                </div>
            
        </>
    )
}

