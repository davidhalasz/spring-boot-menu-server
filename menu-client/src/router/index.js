import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Login from "../views/Login.vue";
import Register from "../views/Register.vue";
import Profile from "../views/Profile.vue";
import DisplayMenu from "../views/DisplayMenu.vue";
import NotFound from "../views/NotFound.vue";
import Unauthorized from "../views/Unauthorized.vue";

const routes = [
  {
    path: "/",
    component: Home,
  },
  {
    path: "/home",
    name: "home",
    component: Home,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/register",
    name: "register",
    component: Register,
  },
  {
    path: "/profile",
    name: "profile",
    component: Profile,
  },
  {
    path: "/menu/show/:id",
    name: "displayMenu",
    component: DisplayMenu,
  },
  {
    path: "/404",
    name: "404",
    component: NotFound,
  },
  {
    path: "/401",
    name: "401",
    component: Unauthorized,
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404",
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
