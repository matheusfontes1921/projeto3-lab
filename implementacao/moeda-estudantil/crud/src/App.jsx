import { useState } from 'react'
import {Route, Routes} from "react-router-dom";
import Login from "./pages/login.jsx";
import AlunoCrud from "./pages/crud-aluno.jsx";
import Home from "./pages/home.jsx";
import CrudEmpresa from "./pages/crud-empresa.jsx";
import Transacao from "./pages/transacao.jsx";
import Professor from "./pages/professor.jsx";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <Routes>
        <Route index element={<Login />} />
        <Route path={"aluno/:id"} element={<AlunoCrud />} />
        <Route path={"home"} element={<Home />} />
        <Route path={"empresa/:id"} element={<CrudEmpresa />} />
        <Route path={"professor/:id"} element={<Professor />} />
        <Route path={"transacao"} element={<Transacao />} />

      </Routes>

    </>
  )
}

export default App
