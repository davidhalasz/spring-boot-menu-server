<template>
  <div class="row">
    <div class="col-6 home-text">
      <div>
        <p class="home-title">Create Your Own Menu</p>
        <p class="home-content">
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus
          auctor ornare lacus sed rutrum. Duis ut nisi metus. Aenean a
          sollicitudin ante, quis tempor urna. Fusce quis consectetur tellus.
        </p>
      </div>
    </div>
    <div class="col-6 images">
      <div class="img1">
        <img src="../assets/home_1.png" />
      </div>
      <div class="img2">
        <img src="../assets/home-qr.png" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=VT323&display=swap");

.home-text {
  font-family: "VT323", monospace;
  margin-top: 10%;
}
.home-title {
  font-size: 50px;
}
.home-content {
  font-size: 20px;
}
.images {
  width: 350px;
  height: 500px;
  position: relative;
  margin-left: 70px;
}
.img1 {
  height: 100%;
  width: auto;
  -ms-transform: skewY(-8deg); /* IE 9 */
  transform: skewY(-8deg);
  position: absolute;
  top: 0;
  left: 0;
  margin-top: 50px;
}
.img1 img {
  height: 100%;
  border: 5px black solid;
}
.img2 {
  height: 50%;
  width: auto;
  -ms-transform: skewY(-8deg); /* IE 9 */
  transform: skewY(-8deg);
  position: absolute;
  z-index: 2;
  top: 0;
  right: 0;
  margin-top: 150px;
}

.img2 img {
  height: 100%;
  border: 5px black solid;
}
</style>
