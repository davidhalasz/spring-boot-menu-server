import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

global.jQuery = require("jquery");
const $ = global.jQuery;
window.$ = $;

import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faHome } from "@fortawesome/free-solid-svg-icons";
import VueHtml2Canvas from "vue-html2canvas";

library.add(faHome);

createApp(App)
  .use(store)
  .use(router)
  .use(VueHtml2Canvas)
  .component("font-awesome-icon", FontAwesomeIcon)
  .mount("#app");
