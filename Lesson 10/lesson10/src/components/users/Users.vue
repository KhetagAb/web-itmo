<template>
  <div class="users datatable">
    <div class="caption">User</div>
    <table>
      <thead>
      <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
      </tr>
      </thead>
      <tbody>
        <UsersTableUser v-for="user in users" :user="user" :key="user.id"/>
      </tbody>
    </table>
  </div>
</template>

<script>
import UsersTableUser from "./UsersTableUser";

export default {
  name: "Users",
  components: {UsersTableUser},
  props: ["users"]
}
</script>

<style scoped>

</style>