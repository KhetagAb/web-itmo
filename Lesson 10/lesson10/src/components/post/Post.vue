<template>
  <article>
    <div class="title"><a href="#" @click.prevent="postPage(post)">
      {{ post.title }}
    </a></div>
    <div class="information">By
      {{ post.userLogin }}
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
        <a href="#">
          {{ getCommentsLength }}
        </a>
      </div>
    </div>
    <template v-if="withComments">
      <Comment v-for="comment in post.comments" :comment="comment" :key="comment.id"/>
    </template>
  </article>
</template>

<script>
import Comment from "./Comment";
export default {
  name: "Post",
  components: {Comment},
  methods: {
    postPage: function (post) {
      this.$root.$emit("onPostPage", post);
    }
  },
  computed: {
    getCommentsLength() {
      if (this.post.comments)
        return Object.values(this.post.comments).length
      else
        return 0
    }
  },
  props: ["post", "withComments"]
}
</script>

<style scoped>
</style>