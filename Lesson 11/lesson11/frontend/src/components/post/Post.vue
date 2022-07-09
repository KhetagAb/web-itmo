<template>
  <article>
    <div class="title"><a href="#" @click.prevent="postPage(post)">
      {{ post.title }}
    </a></div>
    <div class="information">By
      {{ post.user.login }}
    </div>
    <div class="body">
      {{ post.text }}
    </div>
    <div class="footer">
      <div class="left">
        <img src="../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
        <span class="positive-score">+173</span>
        <img src="../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
      </div>
      <div class="right">
        <img src="../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
        <a href="#"> </a>
      </div>
    </div>
  </article>
</template>

<script>

export default {
  name: "Post",
  methods: {
    postPage: function (post) {
      this.$root.$emit("onPostPage", post);
    }
  },
  props: ["post"]
}
</script>

<style scoped>
</style>