import {Link, useParams} from "react-router-dom";

export default function Aluno() {
    const { id } = useParams()

    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center", padding: "10"}}>
            <h2>Pages</h2>
            <div
                style={{
                    display: "flex",
                    justifyContent: "space-evenly",
                    alignItems: "flex-start",
                    height: "90vh",
                    width:"98vw",
                    padding: "10"
                }}
            >
                <Link to={"transferencias"} >Transferencia</Link>
                <Link to={"listaVantgens"}>Lista de Vantgens</Link>
            </div>
        </div>

    )
}