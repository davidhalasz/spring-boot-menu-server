<template>
  <div class="container">
    <div class="card register-card p-3">
      <h3 class="text-center">Create a new Account</h3>
      <form
        @submit.prevent="handleRegister"
        novalidate
        class="submitted ? 'was-validated' : ''"
      >
        <div class="form-group">
          <label for="username">Username</label>
          <input
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
            type="text"
            id="password"
            class="form-control"
            name="password"
            placeholder="Password"
            required
          />
          <div class="invalid-feedback">Password is required.</div>
        </div>

        <div class="btn btn-success w-100 mt-5">Sign Up</div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "register",
};
</script>

<style scoped>
h3 {
  color: white
}
.register-card {
  height: 300px;
  width: 400px;
  background-color: black;
  position: absolute;
  left: 50%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
</style>
