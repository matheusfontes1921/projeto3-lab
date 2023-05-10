import {useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

export default function AlunoCrud() {
    const { id } = useParams();

    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [rg, setRg] = useState("");
    const [cpf, setCpf] = useState("");
    const [curso, setCurso] = useState("");
    const [endereco, setEndereco] = useState("");
    const [saldo, setSaldo] = useState("");
    const [senha, setSenha] = useState("");
    const [listAlunos, setListAlunos] = useState([]);

    useState(() => {
        const alunos = axios.get("http://localhost:8080/alunos").then(res => {
            setListAlunos(res.data)
        })

        console.log(alunos)
    })

    const create = () => {
        event.preventDefault()
        const data = {
            nome: nome,
            email: email,
            senha: senha,
            saldo: saldo,
            cpf: cpf,
            rg: rg,
            endereco: endereco,
            curso: curso,
        }

        console.log(data)

        axios.post("http://localhost:8080/alunos", data, {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
            .catch(res => console.log(res))
    }

    const update = () => {
        event.preventDefault();

        const data = {
            nome: nome,
            email: email,
            senha: senha,
            saldo: saldo,
            cpf: cpf,
            rg: rg,
            endereco: endereco,
            curso: curso
        }

        axios.put(`http://localhost:8080/alunos/${id}`, JSON.stringify(data), {headers: { "Content-Type": "application/json" }})
            .then(res => console.log(res.status))
            .catch(res => console.log(res))
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
                <input
                    type="text"
                    name="nome"
                    onChange={(e) => setNome(e.target.value)}
                    required
                />
                <label>Email</label>
                <input
                    type="text"
                    name="email"
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <label>RG</label>
                <input
                    type="text"
                    name="rg"
                    onChange={(e) => setRg(e.target.value)}
                    required
                />
                <label>CPF</label>
                <input
                    type="text"
                    name="cpf"
                    onChange={(e) => setCpf(e.target.value)}
                    required
                />
                <label>Curso</label>
                <input
                    type="text"
                    name="curso"
                    onChange={(e) => setCurso(e.target.value)}
                    required
                />
                <label>Endereço</label>
                <input
                    type="text"
                    name="endereco"
                    onChange={(e) => setEndereco(e.target.value)}
                    required
                />
                <label>Saldo</label>
                <input
                    type="text"
                    name="saldo"
                    onChange={(e) => setSaldo(e.target.value)}
                    required
                />
                <label>Senha</label>
                <input
                    type="password"
                    name="senha"
                    onChange={(e) => setSenha(e.target.value)}
                    required
                />
                <div className="buttons">
                    <button onClick={create}>Create</button>
                </div>
            </form>

            <h2>Update</h2>
            <form className="form">
                <label>Nome</label>
                <input
                    type="text"
                    name="nome"
                    onChange={(e) => setNome(e.target.value)}
                    required
                />
                <label>Email</label>
                <input
                    type="text"
                    name="email"
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                <label>RG</label>
                <input
                    type="text"
                    name="rg"
                    onChange={(e) => setRg(e.target.value)}
                    required
                />
                <label>CPF</label>
                <input
                    type="text"
                    name="cpf"
                    onChange={(e) => setCpf(e.target.value)}
                    required
                />
                <label>Curso</label>
                <input
                    type="text"
                    name="curso"
                    onChange={(e) => setCurso(e.target.value)}
                    required
                />
                <label>Endereço</label>
                <input
                    type="text"
                    name="endereco"
                    onChange={(e) => setEndereco(e.target.value)}
                    required
                />
                <label>Saldo</label>
                <input
                    type="text"
                    name="saldo"
                    onChange={(e) => setSaldo(e.target.value)}
                    required
                />
                <label>Senha</label>
                <input
                    type="password"
                    name="senha"
                    onChange={(e) => setSenha(e.target.value)}
                    required
                />
                <div className="buttons">
                    <button onClick={update}>Update</button>
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