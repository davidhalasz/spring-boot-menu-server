<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-12 text-center">
        <h1 class="display-1 mt-20">401</h1>
        <div class="display-6 mb-5">
          Unauthorized! Access Permission Denied!
        </div>
        <router-link to="/home" class="btn btn-link"
          >Back to Home Page</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "unauthorized",
};
</script>

<style scoped></style>
