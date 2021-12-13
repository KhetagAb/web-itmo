<template>
  <div class="comment">
    <div class="information">By
    {{ comment.userLogin }}
    </div>
    <div class="body">
    {{ comment.text }}</div>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: ["comment"]
}
</script>

<style scoped>
</style>