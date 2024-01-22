import {ref} from 'vue';
export default function useReservaService (){
    const evento = useState('evento',()=>{})
    
    function selecionarEvento(eventoSelecionado){
        evento.value = eventoSelecionado;
    }  
    
    return {
        evento,
        selecionarEvento
    }
}