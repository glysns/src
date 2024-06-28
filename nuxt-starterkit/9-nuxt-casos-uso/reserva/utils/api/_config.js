import axios from "axios";
export const useApi = () => {
  const waiting = useState('waiting',() => {return false}) 

  const config = useRuntimeConfig()
  const API_BASE_URL = config.public.apiBase;

  const api = axios.create({
    baseURL: API_BASE_URL,
  });

  api.interceptors.request.use(
    (req) => {
      waiting.value=true
      console.log('req waiting', waiting.value)
      req.headers["Content-type"] = "application/json";
      if (req.url?.includes("public") || req.url?.includes("login"))
        console.log("rota publica");
      else {
        const token = localStorage.getItem("token");
        req.headers["Authorization"] = token;
      }
      return req;
    },
    (err) => {
      console.log("request - error");
      return Promise.reject(err);
    }
  );

  api.interceptors.response.use(
    (res) => {
      waiting.value=false;
      console.log('res waiting', waiting.value)
      const { data } = res;
      return { success: true, status: data.status, body: data.body };
    },
    (err) => {
      waiting.value=false;
      if (err.response.status == "409") {
        console.log("business exception");
        const { data } = err.response;
        return { success: false, status: data.status, body: data.body };
      } else {
        console.log('erro NAO 409')
        return err;
      }
    }
  );

  return {
    api,
    waiting
  };
};
