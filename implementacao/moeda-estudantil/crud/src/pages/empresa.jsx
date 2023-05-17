import {Link, useParams} from "react-router-dom";

export default function Empresa() {
    const { id } = useParams();

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
                <Link to={"crud/vantagens"}>Cadastrar Vantagens</Link>
                <Link to={"vantagens"}>Lista de Vantagens</Link>
            </div>
        </div>

    )
}