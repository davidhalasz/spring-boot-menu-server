import { BASE_API_URL } from "../common/Constants";
import axios from "axios";
import { authHeader } from "./base.service";

const API_URL = BASE_API_URL + "/api/menu";

class MenuService {

  saveMenu(menu) {
    return axios.post(API_URL, menu, { headers: authHeader() });
  }

  deleteMenu(menu) {
    return axios.delete(API_URL + "/" + menu.id, { headers: authHeader() });
  }

  getMenu(menu) {
    return axios.get(API_URL + "/" + menu.id, { headers: authHeader() });
  }

  getAllMenus() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  displayMenu(id) {
    return axios.get(BASE_API_URL + "/api/show/" + id);
  }
}

export default new MenuService();
