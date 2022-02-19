<template>
  <div class="container">
    <div class="mt-5">
      <div class="alert alert-danger" v-if="errorMessage">
        {{ errorMessage }}
      </div>
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
                <th scope="col" style="width: 10%">#</th>
                <th scope="col" style="width: 80%">Company Name</th>
                <th scope="col" style="width: 10%">action</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(menu, ind) in menuList" :key="ind">
                <td>{{ menu.id }}</td>
                <td>{{ menu.companyName }}</td>
                <td>
                  <button
                    class="btn btn-primary"
                    @click="editSelectedMenu(menu)"
                  >
                    Edit
                  </button>
                </td>
                <td>
                  <button
                    class="btn btn-danger"
                    @click="deleteSelectedMenuModal(menu, ind)"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <menu-modal
    ref="menuModal"
    @saved="menuSaved"
    :selected-menu="selectedMenu"
  />

  <menu-delete-modal ref="deleteMenuModal" @confirmed="deleteSelectedMenu" />
</template>

<script>
import MenuService from "../services/menu.service";
import MenuModal from "../components/Menu.vue";
import MenuDeleteModal from "../components/MenuDeleteModal.vue";
import Menu from "../models/menu";
import vuex from "vuex";
import { nextTick } from "@vue/runtime-core";

export default {
  name: "profile",
  components: { MenuModal, MenuDeleteModal },
  data() {
    return {
      menuList: [],
      selectedMenu: new Menu(),
      errorMessage: "",
      selectedIndex: undefined,
    };
  },
  computed: {
    ...vuex.mapGetters(["currentUser"]),
  },
  mounted() {
    MenuService.getAllMenus().then((response) => {
      this.menuList = response.data;
      console.log(this.menuList);
    });
  },
  methods: {
    createNewMenu() {
      this.selectedMenu = new Menu();
      nextTick(() => {
        this.$refs["menuModal"].showMenuModal();
      });
    },
    menuSaved(menu) {
      const itemIndex = this.menuList.findIndex((item) => item.id == menu.id);
      if (itemIndex !== -1) {
        this.menuList[itemIndex] = menu;
      } else {
        this.menuList.push(menu);
      }
    },
    editSelectedMenu(menu) {
      this.selectedMenu = Object.assign({}, menu);
      nextTick(() => {
        this.$refs["menuModal"].showMenuModal();
      });
    },
    deleteSelectedMenu() {
      MenuService.deleteMenu(this.selectedMenu)
        .then(() => {
          this.menuList.splice(this.selectedIndex, 1);
        })
        .catch((err) => {
          this.errorMessage = "An error occured!";
          console.log(err);
        });
    },
    deleteSelectedMenuModal(menu, index) {
      this.selectedMenu = menu;
      this.selectedIndex = index;

      nextTick(() => {
        this.$refs["deleteMenuModal"].showDeleteModal();
      });
    },
  },
};
</script>

<style scoped></style>
