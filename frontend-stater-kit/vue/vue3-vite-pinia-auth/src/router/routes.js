import { useAuth } from "@/stores/auth.js";

export default async function routes(to, from, next) {
  if (to.meta?.auth) {
    const auth = useAuth();
    if (auth.token && auth.user) {
      const isAuthenticated = await auth.checkToken();
      if (isAuthenticated) next();
      else next({ name: "login" });
    } else {
      next({ name: "login" });
    }
    console.log(to.name);
  } else {
    next();
  }
}
