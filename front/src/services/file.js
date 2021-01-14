import axios from 'axios'
const apiUrl = 'http://localhost:8080/api'

const fileService = (fileText) => {
    return axios.post(apiUrl, {text:fileText})
}

export default fileService;