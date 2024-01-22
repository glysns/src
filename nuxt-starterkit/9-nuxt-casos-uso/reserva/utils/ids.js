
//https://www.w3schools.com/js/js_arrow_function.asp
export const useIds = () => {
    
    const uuid = () => {
        return Math.random().toString(36).substring(2, 15) +
            Math.random().toString(36).substring(2, 15);
    }
    
    return {
        uuid
    }
}