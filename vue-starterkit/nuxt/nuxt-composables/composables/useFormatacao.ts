export default function (){
    const upper = (text:string) =>{
        return text.toUpperCase();
    }
    const lower = (text:string) =>{
        return text.toLowerCase();
    }

    return {upper, lower};
}