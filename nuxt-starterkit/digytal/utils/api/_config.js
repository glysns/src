import axios from "axios";
export const useApi = () => {
  const waiting = useState('waiting',() => {return false}) 
  //const API_BASE_URL = 'http://localhost:8080'
  //const API_BASE_URL = "https://iza-saas-api-production.up.railway.app/";
  //const API_BASE_URL = process.env.API_BASE_URL || "http://localhost:8080/";
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

/*

get         : retorna um objeto pelo seu id                                                     : users/1
find        : retorna um objeto filtrado por um atributo na url                                 : users/login/admin
filter      : retorna uma lista de objetos filtrados por um atributo                            : users/role/ADMIN
localize    : retorna uma lista de objetos filtrados pelo campo localiza                        : users/localize/my name is
search      : retorna uma lista de objetos filtrados por parametros                             : users?role=ADMIN&active=true

list        : retorna uma lista das representacoes dos objetos filtrados por parametros         : users/list?role=ADMIN&active=true
select      : retorna todos os objetos abreviados {id, nome, descricao} ordenados por name      : users/select

create      : POST      : Criar um novo objeto criando um identificador único
update      : PUT       : Atualiza um objeto identificado pelo seu id
save        : POST      : Cria ou atualiza o objeto recebido com um id predefinido manualmente
modify      : PATCH     : Atualiza parcialmente as características de um objeto
change      : PATCH     : Modifica um único atributo de um objeto
delete      : DELETE    : Determina que o objeto não mais fará parte dos recursos disponíveis na aplicação

*/
