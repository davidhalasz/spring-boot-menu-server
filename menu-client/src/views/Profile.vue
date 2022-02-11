<template>
  <div class="container">
    <div class="mt-5">
      <div class="card">
        <div class="card-header">
          <div class="row">
            <div class="col-6">
              <h3>Added Menu</h3>
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
</template>

<script>
import MenuService from "../services/menu.service";
import vuex from "vuex";

export default {
  name: "profile",
  data() {
    return {
      menuList: [],
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
};
</script>

<style scoped></style>
