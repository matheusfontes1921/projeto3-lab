import {Route, Routes} from "react-router-dom";
import Login from "./pages/login.jsx";
import AlunoCrud from "./pages/crud-aluno.jsx";
import Home from "./pages/home.jsx";
import CrudEmpresa from "./pages/crud-empresa.jsx";
import Transacao from "./pages/transacao.jsx";
import Transferencias from "./pages/tranferencias.jsx";
import Professor from "./pages/Professor.jsx";
import Vantagens from './pages/vantagens.jsx';
import Listagem from './pages/listagem.jsx';
import Empresa from "./pages/empresa.jsx";
import Aluno from "./pages/Aluno.jsx";

function App() {

  return (
    <>
      <Routes>
        <Route index element={<Login />} />
        <Route path={"aluno/:id"} >
          <Route index element={<Aluno /> } />
          <Route path={"transferencias"} element={<Transferencias />}/>
          <Route path={"listaVantgens"} element={<Listagem />} />
        </Route>
        <Route path={"professor/:id"}>
          <Route index element={<Professor />} />
          <Route path={"transferencias"} element={<Transferencias />}/>
          <Route path={"transacao"} element={<Transacao />} />
        </Route>
        <Route path={"empresa/:id"}>
          <Route index element={<Empresa />} />
          <Route path={"vantagens"} element={<Listagem />} />
          <Route path={"crud/vantagens"} element={<Vantagens />} />
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
