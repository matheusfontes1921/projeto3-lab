import React, {useEffect, useState} from 'react';
import '../assets/transacao.css';
import {useParams} from "react-router-dom";
import axios from "axios";

function Transacao() {
    const { id } = useParams();
    const [aluno, setAluno] = useState(0);
    const [quantidade, setQuantidade] = useState(0);
    const [descricao, setDescricao] = useState(0);


    function handleTransfer() {
        event.preventDefault()
        const data = {
            aluno: aluno,
            quantidade: quantidade,
            descricao: descricao
        }

        axios.post(`http://localhost:8080/transferencia/${id}/${aluno}/${quantidade}`, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
            .catch(res => console.log(res))
    }

    return (
        <div className="super-container">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FAEDB7" fill-opacity="1" d="M0,160L12.6,165.3C25.3,171,51,181,76,192C101.1,203,126,213,152,218.7C176.8,224,202,224,227,208C252.6,192,278,160,303,154.7C328.4,149,354,171,379,176C404.2,181,429,171,455,154.7C480,139,505,117,531,138.7C555.8,160,581,224,606,229.3C631.6,235,657,181,682,149.3C707.4,117,733,107,758,96C783.2,85,808,75,834,74.7C858.9,75,884,85,909,96C934.7,107,960,117,985,138.7C1010.5,160,1036,192,1061,218.7C1086.3,245,1112,267,1137,277.3C1162.1,288,1187,288,1213,261.3C1237.9,235,1263,181,1288,165.3C1313.7,149,1339,171,1364,176C1389.5,181,1415,171,1427,165.3L1440,160L1440,0L1427.4,0C1414.7,0,1389,0,1364,0C1338.9,0,1314,0,1288,0C1263.2,0,1238,0,1213,0C1187.4,0,1162,0,1137,0C1111.6,0,1086,0,1061,0C1035.8,0,1011,0,985,0C960,0,935,0,909,0C884.2,0,859,0,834,0C808.4,0,783,0,758,0C732.6,0,707,0,682,0C656.8,0,632,0,606,0C581.1,0,556,0,531,0C505.3,0,480,0,455,0C429.5,0,404,0,379,0C353.7,0,328,0,303,0C277.9,0,253,0,227,0C202.1,0,177,0,152,0C126.3,0,101,0,76,0C50.5,0,25,0,13,0L0,0Z"></path></svg>
            <div className="container">
                <h1>Transação de Moedas</h1>
                  <div className="transaction-form">
                    <div className="form-row">
                        <label htmlFor="aluno">Aluno:</label>
                        <input
                            type="text"
                            id="aluno"
                            placeholder="ID do aluno"
                            onChange={(e) => setAluno(e.target.value)}/>
                    </div>
                    <div className="form-row">
                        <label htmlFor="quantidade">Quantidade:</label>
                        <input
                            type="number"
                            id="quantidade"
                            placeholder="Quantidade de moedas"
                            onChange={(e) => setQuantidade(e.target.value)}
                        />
                    </div>
                    <div className="form-row">
                        <label htmlFor="descricao">Decrição:</label>
                        <input
                            type="text"
                            id="descricao"
                            placeholder="Descrição"
                            onChange={(e) => setDescricao(e.target.value)}
                        />
                        </div>
                    <button className="submit-button" onClick={handleTransfer}>Realizar Transação</button>
                </div>
            </div>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FAEDB7" fill-opacity="1" d="M0,160L12.6,165.3C25.3,171,51,181,76,192C101.1,203,126,213,152,218.7C176.8,224,202,224,227,208C252.6,192,278,160,303,154.7C328.4,149,354,171,379,176C404.2,181,429,171,455,154.7C480,139,505,117,531,138.7C555.8,160,581,224,606,229.3C631.6,235,657,181,682,149.3C707.4,117,733,107,758,96C783.2,85,808,75,834,74.7C858.9,75,884,85,909,96C934.7,107,960,117,985,138.7C1010.5,160,1036,192,1061,218.7C1086.3,245,1112,267,1137,277.3C1162.1,288,1187,288,1213,261.3C1237.9,235,1263,181,1288,165.3C1313.7,149,1339,171,1364,176C1389.5,181,1415,171,1427,165.3L1440,160L1440,320L1427.4,320C1414.7,320,1389,320,1364,320C1338.9,320,1314,320,1288,320C1263.2,320,1238,320,1213,320C1187.4,320,1162,320,1137,320C1111.6,320,1086,320,1061,320C1035.8,320,1011,320,985,320C960,320,935,320,909,320C884.2,320,859,320,834,320C808.4,320,783,320,758,320C732.6,320,707,320,682,320C656.8,320,632,320,606,320C581.1,320,556,320,531,320C505.3,320,480,320,455,320C429.5,320,404,320,379,320C353.7,320,328,320,303,320C277.9,320,253,320,227,320C202.1,320,177,320,152,320C126.3,320,101,320,76,320C50.5,320,25,320,13,320L0,320Z"></path></svg>
        </div>
    )
}

export default Transacao;
