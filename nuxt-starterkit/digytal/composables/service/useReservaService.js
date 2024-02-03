export const useReservaService = () => {
    const evento = useState('evento',()=>{})
    const {createReserva, waiting} = usePublicApi();

    const selecionarEvento = object => evento.value = object;
    
    const confirmarReserva = async (cadastro) => {
        const payload = {
            numeroDocumento: evento.value.tag,
            titulo: evento.value.titulo,
            descricao: evento.value.descricao,
            observacao: cadastro.value.observacao,
            valor: evento.value.valor,
            favorecido: {
              cpfCnpj: cadastro.value.cpf,
              nome: cadastro.value.nome,
              email: cadastro.value.email,
            },
            formasPagamento: [
              {
                meioPagamento: "PIXP",
                valorOriginal: evento.value.valor,
                taxaPagamento: 0.0,
                valorPago: evento.value.valor,
              },
            ],
          };
          console.log("Payload:", JSON.stringify(payload,null, 2));

          return await createReserva(payload)
    }
    
    return {evento,selecionarEvento, confirmarReserva, waiting}
}