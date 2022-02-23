<template>
  <div>
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <div class="container">
        <div class="navbar-nav me-auto">
          <li class="nav-item">
            <router-link class="nav-link" to="/home" active-class="active">
              Home
            </router-link>
          </li>
          <li class="nav-item" v-if="isUser">
            <router-link class="nav-link" to="/profile" active-class="active">
              Profile
            </router-link>
          </li>
        </div>

        <div class="navbar-nav ms-auto" v-if="!currentUser">
          <li class="nav-item">
            <router-link class="nav-link" to="/register" active-class="active">
              Sign Up
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/login" active-class="active">
              Login
            </router-link>
          </li>
        </div>

        <div class="navbar-nav ms-auto" v-if="currentUser">
          <li class="nav-item">
            <a href="#" class="nav-link" @click="logout">Logout</a>
          </li>
        </div>
      </div>
    </nav>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<script>
import vuex from 'vuex';
import Role from './models/role';

export default {
  computed: {
    ...vuex.mapGetters(['currentUser']),
    isUser() {
      return this.currentUser?.role == Role.USER;
    },
  },
  methods: {
    ...vuex.mapActions(['clearUser']),
    logout() {
      this.clearUser();
      this.$router.push('/login');
    },
  },
}
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=VT323&display=swap");

.navbar {
  font-family: "VT323", monospace;
  font-size: 25px;
}
</style>
