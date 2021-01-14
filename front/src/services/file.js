import axios from 'axios'
const apiUrl = 'http://localhost:8080/'

const fileService = (fileText) => {
    const config = {}
    return axios.get(apiUrl, config)
}

export default fileService;