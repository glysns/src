export default defineNuxtRouteMiddleware( (to, from) => {
    const param = +to.params.id;
    if(isNaN(param) || param <0)
        return navigateTo("/auth/login")
});