<template>
  <div class="container">
    <div class="mt-5">
      <div class="alert alert-danger" v-if="errorMessage">
        {{ errorMessage }}
      </div>
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col-6"></div>
            <div class="col-6 text-end">
              <button class="actionBtn" @click="createNewMenu">
                <div class="btn-txt">New Menu</div>
              </button>
            </div>
          </div>
        </div>

        <div class="card-body">
          <table class="table table-hover">
            <thead class="thead-dark bg-dark text-light">
              <tr>
                <th scope="col" style="width: 10%">#</th>
                <th scope="col" style="width: 55%">Company Name</th>
                <th scope="col" style="width: 20%" class="text-center">
                  QR code
                </th>
                <th scope="col" style="width: 5%"></th>
                <th scope="col" style="width: 5%"></th>
                <th scope="col" style="width: 5%"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(menu, ind) in menuList" :key="ind">
                <td>{{ menu.id }}</td>
                <td class="name-txt">
                  <router-link
                    :to="{ name: 'displayMenu', params: { id: menu.id } }"
                  >
                    {{ menu.companyName }}</router-link
                  >
                </td>
                <td class="text-center">
                  <qrcode-vue
                    :value="urls[ind]"
                    size="120"
                    level="H"
                    :id="downloadIds[ind]"
                  >
                  </qrcode-vue>
                </td>
                <td>
                  <a :id="'download' + ind" download="download.png">
                    <button
                      class="actionBtn"
                      @click="
                        downloadQR(downloadIds[ind], menu.companyName, ind)
                      "
                    >
                      <div class="btn-txt">Donwload</div>
                    </button>
                  </a>
                </td>
                <td>
                  <button class="actionBtn" @click="editSelectedMenu(menu)">
                    <div class="btn-txt">Edit</div>
                  </button>
                </td>
                <td>
                  <button
                    class="actionBtn delete"
                    @click="deleteSelectedMenuModal(menu, ind)"
                  >
                    <div class="btn-txt delete-txt">Delete</div>
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
import QrcodeVue from "qrcode.vue";
import { BASE_CLIENT_URL } from "../common/Constants";

export default {
  name: "profile",
  components: { MenuModal, MenuDeleteModal, QrcodeVue },
  data() {
    return {
      menuList: [],
      urls: [],
      downloadIds: [],
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
      this.menuList.forEach((value) => {
        this.urls.push(BASE_CLIENT_URL + "/menu/show/" + value.id);
        this.downloadIds.push("canvas" + value.id);
      });
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
    async downloadQR(canvasId, name, index) {
      var companyName = name.split(" ").join("_");

      var download = document.getElementById("download" + index);
      var image = document
        .getElementById(canvasId)
        .toDataURL("image/png")
        .replace("image/png", "image/octet-stream");
      download.setAttribute("href", image);
      download.setAttribute("download", companyName + ".png");
    },
  },
};
</script>

<style scoped>
.card-header {
  background-color: transparent;
  background-image: url("../assets/table_header_bg.png");
}

.actionBtn {
  height: 60px;
  width: 120px;
  background-color: white;
  border: 5px black solid;
}
.delete {
  border: 5px red solid;
}
.btn-txt {
  color: white;
  font-weight: bold;
  background-color: black;
  padding: 5px;
}
.delete-txt {
  background-color: red;
}
.table tr td {
  vertical-align: middle;
}
.name-txt {
  font-weight: bold;
  font-size: 18px;
  color: black;
}
.name-txt a {
  color: black;
}
</style>
