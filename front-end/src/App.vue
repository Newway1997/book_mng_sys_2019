<template>
    <el-container id="app">
      <el-header>
        <Navbar/>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
</template>

<script>
import Navbar from "@/components/Navbar";
import BooksList from "@/components/BooksList";
export default {
  name: "App",
  components: {
    Navbar: Navbar,
    BooksList: BooksList
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
body {
  margin: 0;
  padding: 0;
  height: 100vh;
}
.el-header {
  padding: 0;
}
</style>
