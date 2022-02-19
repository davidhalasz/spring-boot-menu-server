<template>
  <div class="container">
    <div class="row justify-content-md-center">
      <div class="mx-auto col-md-12 col-sm-12 pt-5 pb-5 text-center">
        <h2>{{ menu.companyName }}</h2>
      </div>
    </div>
    <div class="justify-content-center">
      <div
        class="mx-auto col-md-6 col-sm-12 col-xs-12 col-sm-6 pb-5"
        v-for="(type, ind) in types"
        :key="ind"
      >
        <div class="row col-md-12 category">
          <div class="col">
            <h4>{{ type }}</h4>
          </div>
          <div class="col text-end">Price</div>
        </div>
        <div
          class="row col-md-12"
          v-for="(prod, index) in menu.products"
          :key="index"
        >
          <div class="col" v-if="type == menu.types[index]">
            <div class="p-1">{{ menu.products[index] }}</div>
          </div>
          <div class="col text-end" v-if="type == menu.types[index]">
            <div class="p-1">{{ menu.prices[index] }} Ft</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MenuService from "../services/menu.service";
import Menu from "../models/menu";

export default {
  name: "displayMenu",
  data() {
    return {
      types: [],
      menu: new Menu(),
    };
  },
  mounted() {
    MenuService.displayMenu(this.$route.params.id).then((response) => {
      this.menu = response.data;
      this.types = [...new Set(this.menu.types)];
      console.log(this.types);
    });
  },
};
</script>

<style scoped>
.category {
  border-bottom: 1px solid black;
}
</style>
