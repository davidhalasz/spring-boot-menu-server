<template>
  <div class="container">
    <div class="card login-card p-3">
      <h3 class="text-center">Login</h3>

      <div v-if="errorMessage" class="alert alert-danger">
        {{ errorMessage }}
      </div>
      <form
        @submit.prevent="handleLogin"
        novalidate
        :class="submitted ? 'was-validated' : ''"
      >
        <div class="form-group">
          <label for="username">Username</label>
          <input
            v-model="formData.username"
            type="text"
            id="username"
            class="form-control"
            name="username"
            placeholder="Username"
            required
          />
          <div class="invalid-feedback">Username is required.</div>
        </div>

        <div class="form-group">
          <label for="password">Password</label>
          <input
            v-model="formData.password"
            type="text"
            id="password"
            class="form-control"
            name="password"
            placeholder="Password"
            required
          />
          <div class="invalid-feedback">Password is required.</div>
        </div>
        +

        <button
          class="btn btn-success w-100 mt-5"
          @click="submitted = true"
          :disabled="loading"
        >
          Login
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import User from "../models/user";
import vuex from "vuex";
import AuthenticationService from "../services/authentication.service";

export default {
  name: "login",
  data() {
    return {
      formData: new User(),
      loading: false,
      submitted: false,
      errorMessage: "",
    };
  },
  computed: {
    ...vuex.mapGetters(["currentUser"]),
  },
  mounted() {
    if (this.currentUser?.username) {
      this.$router.push("/profile");
    }
  },
  methods: {
    ...vuex.mapActions(["updateUser"]),
    handleLogin() {
      if (!this.formData.username || !this.formData.password) {
        return;
      }

      this.loading = true;

      AuthenticationService.login(this.formData)
        .then((response) => {
          this.updateUser(response.data);
          this.$router.push("/profile");
        })
        .catch((err) => {
          console.log(err);
          this.errorMessage = "An error occured!";
        })
        .then(() => (this.loading = false));
    },
  },
};
</script>

<style scoped>
h3 {
  color: white;
}
.login-card {
  width: 400px;
  background-color: black;
  position: absolute;
  left: 50%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
label {
  color: antiquewhite;
}
</style>
