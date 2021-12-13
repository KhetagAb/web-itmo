<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2021 by Mike Mirzayanov
      <template v-if="usersCount">
        | Users: {{ usersCount }}
      </template>
      <template v-if="postsCount">
        | Posts: {{ postsCount }}
      </template>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["usersCount", "postsCount"],
}
</script>

<style scoped>

</style>