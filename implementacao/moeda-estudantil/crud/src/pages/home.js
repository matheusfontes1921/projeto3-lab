import {Link} from "react-router-dom";

export default function Home() {
    return(
        <div style={{display: "flex", flexDirection: "column"}}>
            <h1>CRUDs</h1>
            <Link to={"/aluno"} >empresa</Link>
            <Link to={"/empresa"}>empresa</Link>
        </div>
    )
}