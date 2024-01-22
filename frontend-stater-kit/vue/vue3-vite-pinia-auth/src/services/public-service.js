import http from '@/services/http.js';

const publicService = {
    async logar(user) {
        console.log(user);
        return http.post('/public/login', user)
    },
  };

export default publicService;
  
