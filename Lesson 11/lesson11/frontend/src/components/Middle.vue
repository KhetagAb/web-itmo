<template>
  <div class="middle">
    <Sidebar :posts="sidePosts"/>
    <main>
      <Index v-if="page === 'Index'" :posts="getPosts"/>
      <Enter v-if="page === 'Enter'"/>
      <Users v-if="page === 'Users'" :users="users"/>
      <Register v-if="page === 'Register'"/>
    </main>
  </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./main/Index";
import Enter from "./main/Enter";
import Register from "./main/Register";
import Users from "./users/Users";

export default {
  name: "Middle",
  data: function () {
    return {
      page: "Index",
      logins: {}
    }
  },
  components: {
    Users,
    Register,
    Enter,
    Index,
    Sidebar
  },
  props: ["posts", "users"],
  computed: {
    getPosts: function () {
      return Object.values(this.posts).sort((a, b) => b.id - a.id);
    },
    sidePosts: function () {
      return this.getPosts.slice(0, 2);
    },
  }, beforeCreate() {
    this.$root.$on("onChangePage", (page) => this.page = page)
  }
}
</script>

<style scoped>

</style>
