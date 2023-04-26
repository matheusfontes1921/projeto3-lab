import {useState} from "react";
import axios from "axios";

export default function AlunoCrud() {
    const [nome, setNome] = useState("");
    const [rg, setRg] = useState("");
    const [curso, setCurso] = useState("");
    const [endereco, setEndereco] = useState("");
    const [saldo, setSaldo] = useState("");
    const [id, setId] = useState("")
    const [listAlunos, setListAlunos] = useState([]);

    useState(() => {
        const alunos = axios.get("http://localhost:8080/alunos").then(res => {
            setListAlunos(res.data)
        })

        console.log(alunos)
    })

    const create = () => {
        const data = {
            nome: nome,
            rg: rg,
            curso: curso,
            endereco: endereco,
            saldo: saldo
        }

        axios.post("http://localhost:8080/alunos", data, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
    }

    const update = () => {
        const data = {
            nome: nome,
            rg: rg,
            curso: curso,
            endereco: endereco,
            saldo: saldo
        }

        axios.put(`http://localhost:8080/alunos/{id}`, data, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
    }

    const deleteAccount = () => {
        axios.delete(`http://localhost:8080/alunos/{id}`, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
    }



    return (
        <div className="App">
            <h1> CRUD de alunos </h1>

            <h2>Create</h2>
            <form className="form">
                <label>Nome</label>
                <input type="text" name="nome" onChange={(e) => setNome(e.target)} required />
                <label>RG</label>
                <input type="text" name="rg" onChange={(e) => setRg(e.target)} required />
                <label>Curso</label>
                <input type="text" name="curso" onChange={(e) => setCurso(e.target)} required />
                <label>Endereço</label>
                <input type="text" name="endereco" onChange={(e) => setEndereco(e.target)} required />
                <label>Saldo</label>
                <input type="text" name="saldo" onChange={(e) => setSaldo(e.target)} required />
                <div className="buttons">
                    <button onClick={create}>Create</button>
                </div>
            </form>

            <h2>Update</h2>
            <form className="form">
                <label>Id</label>
                <input type="text" name="nome" onChange={(e) => setId(e.target)} required />
                <label>Nome</label>
                <input type="text" name="nome" onChange={(e) => setNome(e.target)} required />
                <label>RG</label>
                <input type="text" name="rg" onChange={(e) => setRg(e.target)} required />
                <label>Curso</label>
                <input type="text" name="curso" onChange={(e) => setCurso(e.target)} required />
                <label>Endereço</label>
                <input type="text" name="endereco" onChange={(e) => setEndereco(e.target)} required />
                <label>Saldo</label>
                <input type="text" name="saldo" onChange={(e) => setSaldo(e.target)} required />
                <div className="buttons">
                    <button onClick={create}>Create</button>
                </div>
            </form>

            <h2>Delete</h2>
            <form className="form">
                <input type="text" name="nome" onChange={(e) => setId(e.target)} required />
                <div className="buttons">
                    <button onClick={deleteAccount}>Delete</button>
                </div>
            </form>
        </div>
    );
}