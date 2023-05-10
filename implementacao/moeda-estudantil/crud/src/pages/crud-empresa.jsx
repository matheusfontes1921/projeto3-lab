import {useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

export default function CrudEmpresa() {
    const { id } = useParams()

    const [nome, setNome] = useState("");
    const [idEmpresa, setIdEmpresa] = useState("");
    const [idVantagem, setIdVantagem] = useState("");
    const [listEmpresas, setListEmpresas] = useState([]);

    useState(() => {
        const empresas = axios.get("http://localhost:8080/empresas").then(res => {
            setListEmpresas(res.data)
        })

        console.log(empresas)
    })

    const create = () => {
        const data = {
            nome: nome,
            idEmpresa: idEmpresa,
            idVantagem: idVantagem
        }

        axios.post("http://localhost:8080/empresas", data, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
    }

    const update = () => {
        const data = {
            nome: nome,
            idEmpresa: idEmpresa,
            idVantagem: idVantagem
        }

        axios.put(`http://localhost:8080/empresas/{id}`, data, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
    }

    const deleteAccount = () => {
        axios.delete(`http://localhost:8080/empresas/{id}`, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
    }



    return (
        <div className="App">
            <h1> CRUD de empresas parceiras </h1>

            <h2>Create</h2>
            <form className="form">
                <label>Nome da empresa</label>
                <input type="text" name="nome" onChange={(e) => setNome(e.target)} required />
                <label>ID da empresa</label>
                <input type="text" name="rg" onChange={(e) => setIdEmpresa(e.target)} required />
                <label>ID da vantagem</label>
                <input type="text" name="curso" onChange={(e) => setIdVantagem(e.target)} required />
                <div className="buttons">
                    <button onClick={create}>Create</button>
                </div>
            </form>

            <h2>Update</h2>
            <form className="form">
                <label>Id</label>
                <input type="text" name="nome" onChange={(e) => setId(e.target)} required />
                <label>Nome da empresa</label>
                <input type="text" name="nome" onChange={(e) => setNome(e.target)} required />
                <label>ID da empresa</label>
                <input type="text" name="rg" onChange={(e) => setIdEmpresa(e.target)} required />
                <label>ID da vantagem</label>
                <input type="text" name="curso" onChange={(e) => setIdVantagem(e.target)} required />
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