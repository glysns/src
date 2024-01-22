import axios from 'axios';

const http = axios.create({
  baseURL: 'local',
  headers: {
    'Content-type': 'application/json'
  }
});

export default http;