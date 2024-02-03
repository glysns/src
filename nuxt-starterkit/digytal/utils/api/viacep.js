
//https://www.w3schools.com/js/js_arrow_function.asp
export const useViaCepApi = () => {
    const getCepMock = () => {
        return {
            "cep": "64000-040",
            "logradouro": "Praça da Liberdade",
            "complemento": "",
            "bairro": "Centro",
            "localidade": "Teresina",
            "uf": "PI",
            "ibge": "2211001",
            "gia": "",
            "ddd": "86",
            "siafi": "1219"
          }
    }
    //https://www.w3schools.com/jsref/jsref_tostring_number.asp
    const getCep = async (id) => {
        console.log('CEP RECEBIDO', id)
        id = id.toString().replaceAll(/\D/g,'')
        console.log('CEP TRANSFORMADO', id)
        return await useFetch(`https://viacep.com.br/ws/${id}/json/`);
    }

    return {
        getCepMock,
        getCep
        
    }
}