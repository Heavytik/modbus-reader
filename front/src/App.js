import React, { useState } from 'react'
import './App.css';
import fileService from './services/file'

function App() {

  const [fileText, setFileText] = useState("")
  const [data, setData] = useState("")

  const processFile = async () => {
    const inputFile = document.getElementById("input").files[0]
    const content = await inputFile.text()
    setFileText(content)
    const response = await fileService(content)
    console.log(response.data)
    setData(response.data)
  }

  return (
    <div className="App">
      <header className="App-header">
        <h1>
          MODBUS Reader
        </h1>
      </header>
      <main>
        <div>
          <input type="file" id="input" multiple />
          <button onClick={processFile}>Send and process file</button>
          <div>{data}</div>
        </div>
      </main>
    </div>  
  );
}

export default App;
