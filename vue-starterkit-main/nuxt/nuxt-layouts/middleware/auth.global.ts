const logado = false;
export default defineNuxtRouteMiddleware( (to, from) => {
    if(to.path !== "/auth/login" && !logado)
        return navigateTo("/auth/login")
});