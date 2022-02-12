<template>
  <div class="container">
    <div class="mt-5">
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col-6">
              <h3>Added Menu</h3>
            </div>
            <div class="col-6 text-end">
              <button class="btn btn-primary" @click="createNewMenu">
                New Menu
              </button>
            </div>
          </div>
        </div>

        <div class="card-body">
          <table class="table">
            <thead>
              <tr>
                <th scope="col">username</th>
                <th scope="col">description</th>
                <th scope="col">action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, ind) in menuList" :key="ind">
                <td>{{ item.id }}</td>
                <td>{{ item.products }}</td>
                <td><button class="btn btn-primary">Edit</button></td>
                <td><button class="btn btn-danger">Delete</button></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <menu-modal ref="menuModal"/>
</template>

<script>
import MenuService from "../services/menu.service";
import MenuModal from "../components/Menu.vue";
import Menu from "../models/menu";
import vuex from "vuex";

export default {
  name: "profile",
  components: { MenuModal },
  data() {
    return {
      menuList: [],
      selectedMenu: new Menu(),
    };
  },
  computed: {
    ...vuex.mapGetters(["currentUser"]),
  },
  mounted() {
    MenuService.getAllMenus().then((response) => {
      this.menuList = response.data;
    });
  },
  methods: {
    createNewMenu() {
      this.$refs["menuModal"].showMenuModal();
    },
  },
};
</script>

<style scoped></style>
