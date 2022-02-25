<template>
  <div class="modal fade" id="deleteModal" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Delete Menu</h5>
          <button
            class="btn-close"
            type="button"
            data-bs-dismiss="modal"
          ></button>
        </div>

        <div class="modal-body">Are you sure to delete this menu?</div>

        <div class="modal-footer">
          <button
            class="actionBtn"
            type="button"
            data-bs-dismiss="modal"
          >
            <div class="btn-txt">Cancel</div>
          </button>
          <button class="actionBtn deleteBtn" type="button" @click="deleteMenu">
            <div class="btn-txt btn-delete">Delete</div>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "menu-delete-modal",
  methods: {
    showDeleteModal() {
      $("#deleteModal").modal("show");
    },
    deleteMenu() {
      this.$emit("confirmed");
      $("#deleteModal").modal("hide");
    },
  },
};
</script>

<style scoped>
.actionBtn {
  height: 60px;
  width: 120px;
  background-color: white;
  border: 5px black solid;
}
.deleteBtn {
  border: 5px red solid;
}
.btn-txt {
  color: white;
  font-weight: bold;
  background-color: black;
  padding: 5px;
}
.btn-delete {
  background-color: red;
}
</style>
