import './App.css';

function App() {
  return (
    <div className="App">
    <h1> CRUD de alunos </h1>

  <div className="form">
    <label>Nome</label>
    <input type="text" name="nome" />
    <label>RG</label>
    <input type="text" name="rg" />
    <label>Curso</label>
    <input type="text" name="curso" />
    <label>Endereço</label>
    <input type="text" name="endereco" />
    <label>Saldo</label>
    <input type="text" name="saldo" />
    <div className="buttons">
    <button>Create</button>
    <button>Update</button>
    <button>Delete</button>
    </div>
    </div>
    </div>
  );
}

export default App;
