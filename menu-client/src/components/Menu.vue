<template>
  <div class="modal fade" id="menuModal" tabIndex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <form
          @submit.prevent="saveMenu"
          novalidate
          :class="submitted ? 'was-validated' : ''"
        >
          <div class="modal-header">
            <h5 class="modal-title">Menu Details</h5>
            <button
              class="btn-close"
              type="button"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <div class="modal-body">
            <div class="alert alert-danger" v-if="errorMessage">
              <strong>Error!</strong> {{ errorMessage }}
            </div>

            <div class="form-group">
              <label for="products">Products</label>
              <input
                v-model="menu.products"
                type="text"
                id="products"
                name="products"
                class="form-control"
                required
              />
              <div class="invalid-feedback">Products is required.</div>
            </div>
          </div>

          <div class="modal-footer">
            <button
              class="btn btn-secondary"
              type="button"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button
              class="btn btn-primary"
              type="submit"
              @click="submitted = true"
            >
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Menu from "../models/menu";
import MenuService from "../services/menu.service";
import $ from "jquery";
import vuex from "vuex";

export default {
  name: "menu-modal",
  data() {
    return {
      menu: new Menu(),
      errorMessage: "",
      submitted: false,
    };
  },
  computed: {
    ...vuex.mapGetters(["currentUser"]),
  },
  methods: {
    saveMenu() {
      if (!this.menu.products) {
        return;
      }

      MenuService.saveMenu(
        Object.assign(this.$data.menu, { userId: this.currentUser.id })
      )
        .then((response) => {
          this.$emit("saved", response.data);
          $("#menuModal").modal("hide");
        })
        .catch((err) => {
          this.errorMessage = "An error occured!";
          console.log(err);
        });
    },
    showMenuModal() {
      $("#menuModal").modal("show");
    },
  },
};
</script>
