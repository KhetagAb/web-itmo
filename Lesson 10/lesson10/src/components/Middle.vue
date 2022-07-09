<template>
    <div class="middle">
        <Sidebar :posts="sidePosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="getPosts"/>
            <Enter v-if="page === 'Enter'"/>
            <Register v-if="page === 'Register'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Post v-if="page === 'Post'" :post="this.post" :with-comments="true"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "./sidebar/Sidebar";
import Index from "./page/Index";
import Enter from "./page/Enter";
import WritePost from "./page/WritePost";
import EditPost from "./page/EditPost";
import Register from "./page/Register";
import Users from "./users/Users";
import Post from "./post/Post";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: undefined,
            logins: {}
        }
    },
    components: {
      Post,
      Users,
      Register,
      WritePost,
      Enter,
      Index,
      Sidebar,
      EditPost
    },
    methods: {
      findUserById(id) {
        return Object.values(this.users).find(u => u.id === id);
      }
    },
    props: ["posts", "users", "comments"],
    computed: {
        getPosts: function () {
          Object.values(this.users).forEach(u => this.logins[u.id] = u.login);
          Object.values(this.comments).forEach(comment => {
            if (this.posts[comment.postId].comments === undefined) {
              this.posts[comment.postId].comments = {}
            }

            comment.userLogin = this.logins[comment.userId]
            this.posts[comment.postId].comments[comment.id] = comment
          });

          return Object.values(this.posts).sort((a, b) => b.id - a.id).map(post => {
            post.userLogin = this.logins[post.userId];
            return post;
          });
        },
        sidePosts: function () {
            return this.getPosts.slice(0, 2);
        },
    }, beforeCreate() {
        this.$root.$on("onPostPage", (post) => { this.page = "Post"; this.post = post; })
        this.$root.$on("onChangePage", (page) => this.page = page)
    }
}
</script>

<style scoped>

</style>
