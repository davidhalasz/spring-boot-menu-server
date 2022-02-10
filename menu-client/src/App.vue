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
          <li class="nav-item">
            <router-link class="nav-link" to="/profile" active-class="active">
              Profile
            </router-link>
          </li>
        </div>

        <div class="navbar-nav ms-auto">
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
      </div>
    </nav>

    <div class="container">
      <router-view />
    </div>
  </div>
</template>

<style></style>
