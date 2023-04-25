import './App.css';

function App() {
  return (
    <div className="App">
      <div className="form">
        <h1>CRUD de empresas parceiras</h1>
        <label>Nome da empresa</label>
        <input type="text" name="nome"/>
        <label>ID da empresa</label>
        <input type="text" name="id_empresa" />
        <label>ID da vantagem</label>
        <input type="text" name="id_vantagem" />
        <button>Submit</button>
      </div>
    </div>
  );
}

export default App;
