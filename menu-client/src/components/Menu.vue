<template>
  <div class="modal fade" id="menuModal" tabIndex="-1">
    <div class="modal-dialog modal-lg">
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
              <label for="companyName">Company Name</label>
              <input
                v-model="companyName"
                type="text"
                id="companyName"
                name="companyName"
                class="form-control"
                required
              />
              <div class="invalid-feedback">Company name is required.</div>
            </div>
            <div class="row" v-for="(input, index) in inputs" :key="index">
              <div class="col-2">
                <div class="form-group">
                  <label for="types">Type</label>
                  <select
                    v-model="input.types"
                    id="types"
                    name="types"
                    class="form-select"
                    aria-label="Default select example"
                  >
                    <option selected value="starters">Starters</option>
                    <option value="soups">Soups</option>
                    <option value="mains">Mains</option>
                    <option value="desserts">Desserts</option>
                    <option value="drinks">Drinks</option>
                  </select>
                  <div class="invalid-feedback">Type name is required.</div>
                </div>
              </div>

              <div class="col-6">
                <div class="form-group">
                  <label for="products">Products</label>
                  <input
                    v-model="input.products"
                    type="text"
                    id="products"
                    name="products"
                    class="form-control"
                    required
                  />
                  <div class="invalid-feedback">Products is required.</div>
                </div>
              </div>

              <div class="col-2">
                <div class="form-group">
                  <label for="prices">Prices</label>
                  <input
                    v-model="input.prices"
                    type="number"
                    min="0"
                    id="prices"
                    name="prices"
                    class="form-control"
                    required
                  />
                  <div class="invalid-feedback">Price is required.</div>
                </div>
              </div>
              <div class="col-1 text-center mt-3">
                <i
                  @click="addInputs(index)"
                  v-show="index == inputs.length - 1"
                  class="bi bi-plus-circle-fill"
                  style="font-size: 28px; color: green"
                ></i>
              </div>
              <div class="col-1 text-center mt-3">
                <i
                  @click="removeInputs(index)"
                  v-show="index || (!index && inputs.length > 1)"
                  class="bi bi-trash3-fill"
                  style="font-size: 28px; color: red"
                ></i>
              </div>
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
            <button class="btn btn-primary" type="submit" @click="save">
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
      companyName: "",
      inputs: [
        {
          types: "starters",
          products: "",
          prices: 0,
        },
      ],
      menu: new Menu(),
      errorMessage: "",
      submitted: false,
    };
  },
  computed: {
    ...vuex.mapGetters(["currentUser"]),
  },
  methods: {
    showMenuModal() {
      $("#menuModal").modal("show");
    },
    addInputs(index) {
      if (
        !this.inputs[index].types ||
        !this.inputs[index].products ||
        !this.inputs[index].prices
      ) {
        this.errorMessage = "The fields cannot be empty!";
        return;
      }
      this.errorMessage = "";
      this.inputs.push({
        types: "",
        products: "",
        prices: 0,
      });
      console.log(this.inputs);
    },
    removeInputs(index) {
      this.inputs.splice(index, 1);
    },
    save() {
      this.submitted = true;
      var typesLst = [];
      var productsLst = [];
      var pricesLst = [];
      this.inputs.forEach((element) => {
        for (let [key, value] of Object.entries(element)) {
          if (key == "types") {
            typesLst.push(value);
          }

          if (key == "products") {
            productsLst.push(value);
          }

          if (key == "prices") {
            pricesLst.push(value);
          }
        }
      });

      this.menu.userId = this.currentUser.id;
      this.menu.companyName = this.companyName;
      this.menu.types = typesLst;
      this.menu.products = productsLst;
      this.menu.prices = pricesLst;

      MenuService.saveMenu(this.menu)
        .then((response) => {
          this.$emit("saved", response.data);
          $("#menuModal").modal("hide");
        })
        .catch((err) => {
          this.errorMessage = "An error occured!";
          console.log(err);
        });

    },
  },
};
</script>
