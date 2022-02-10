<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-12 col-lg-8, col-xl-6 text-center">
        <h1 class="display-1 mt-20">404</h1>
        <div class="display-6 mb-5">Opps! Page not found!</div>
        <router-link to="/home" class="btn btn-link"
          >Back to Home Page</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "notfound",
};
</script>

<style scoped></style>
