import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import '../assets/listagem.css';



export default function Listagem(){
    return (
        <div className="wrapper">
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
      
      </Table>
    </TableContainer>
    </div>
    )
}