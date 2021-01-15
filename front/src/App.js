import React, { useState } from 'react'
import './App.css';
import fileService from './services/file'

function App() {

  const [data, setData] = useState([])

  const processFile = async () => {
    const inputFile = document.getElementById("input").files[0]
    const content = await inputFile.text()
    const response = await fileService(content)
    
    setData(parseResponse(response.data))
  }

  const parseResponse = (res) => {
    const trimmed = res.trim()
    const noBraces = trimmed.substring(1, trimmed.length - 1)
    const elemArray = noBraces.split(",")
    return elemArray.map(e => {
      const values = e.split(":")
      return {
        description: values[0].trim(),
        value: values[1].trim(),
        unit: values[2].trim()
      }
    })
  }

  const dataList = data.map(d => <tr key={d.description}><td>{d.description}</td><td>{d.value}</td><td>{d.unit}</td></tr>)

  return (
    <div className="App">
      <header className="App-header">
        <h1>
          MODBUS Reader
        </h1>
      </header>
      <main>
        <div className="container">
          <div>
            <input type="file" id="input" multiple />
            <button onClick={processFile}>Send and process file</button>
          </div>
          <div>
            <table>
              <thead>
                <tr>
                  <th>Description</th>
                  <th>Value</th>
                  <th>Unit</th>
                </tr>
              </thead>
              <tbody>
                {dataList}
              </tbody>
            </table>
          </div>
        </div>
      </main>
    </div>  
  );
}

export default App;
