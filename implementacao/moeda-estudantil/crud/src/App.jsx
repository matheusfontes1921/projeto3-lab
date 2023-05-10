import { useState } from 'react'
import {Route, Routes} from "react-router-dom";
import Login from "./pages/login.jsx";
import AlunoCrud from "./pages/crud-aluno.jsx";
import Home from "./pages/home.jsx";
import CrudEmpresa from "./pages/crud-empresa.jsx";
import Transacao from "./pages/transacao.jsx";
import Transferencias from "./pages/tranferencias.jsx";
import Professor from "./pages/Professor.jsx";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Routes>
        <Route index element={<Login />} />
        <Route path={"aluno"} >
          <Route path={"transferencias/:id"} element={<Transferencias />}/>
        </Route>
        <Route path={"professor/:id"}>
          <Route index element={<Professor />} />
          <Route path={"transferencias"} element={<Transferencias />}/>
          <Route path={"transacao"} element={<Transacao />} />
        </Route>
        <Route path={"home"} element={<Home />} />
        <Route path={"crud"}>
          <Route path={"aluno"} element={<AlunoCrud />} />
          <Route path={"empresa"} element={<CrudEmpresa />} />
        </Route>

      </Routes>

    </>
  )
}

export default App
