<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    :router="true"
  >
    <el-menu-item index="1" route="/">首页</el-menu-item>

    <el-menu-item index="2" class="right" route="/register" v-if="!islogin">注册</el-menu-item>
    <el-menu-item index="3" class="right" route="/login" v-if="!islogin">登录</el-menu-item>
    <el-submenu index="4" class="right" route="/user" v-else-if="islogin&&user.type===1">
      <template slot="title">用户</template>
      <el-menu-item index="2-1" :route="'/user/'+user.id">个人信息</el-menu-item>
      <el-menu-item index="2-2" route="/borrowbook">我的借阅</el-menu-item>
      <el-menu-item index="2-3" route="/updatePass">修改密码</el-menu-item>
      <el-menu-item index="2-4" @click="logout">注销</el-menu-item>
    </el-submenu>
    <el-submenu index="4" class="right" route="/user" v-else-if="islogin&&user.type===0">
      <template slot="title">用户</template>
      <el-menu-item index="2-1" route="/admin/manageUser">用户管理</el-menu-item>
      <el-menu-item index="2-2" route="/admin/manageBook">书籍管理</el-menu-item>
      <el-menu-item index="2-3" route="/updatePass">修改密码</el-menu-item>
      <el-menu-item index="2-4" @click="logout">注销</el-menu-item>
    </el-submenu>
  </el-menu>
</template>
<script>
export default {
  name: "Navbar",
  data() {
    return {
      user:{}
    };
  },
  computed: {
    islogin: function() {
      if (this.$store.getters.isLogin) {
        this.checkUser();
        
      } else {
        this.user = {};
      }
      return this.$store.getters.isLogin;
    }
  },
  methods: {
    logout: function() {
      this.$http.get(`${this.$store.state.origin}/api/user/logout`).then(
        res => {
          res.json().then(body => {
            if (body.meta.success === true) {
              this.$store.commit("userLogout");
              this.user = {};
              this.$router.push({
                //你需要接受路由的参数再跳转
                path: "/"
              });
            } else {
              this.$message({
                showClose: true,
                message: body.meta.message,
                type: "error"
              });
            }
          });
        },
        function() {
          console.log("请求失败处理");
        }
      );
    },
    checkUser: function() {
      //发送get请求
      this.$http.get(`${this.$store.state.origin}/api/user/checkUser`).then(
        res => {
          res.json().then(body => {
          
            if (body.meta.success == true) {
              this.user = body.data;
              
            }else{
              this.$store.commit("userLogout");
            }
          });
        },
        function() {
          this.$store.commit("userLogout");
        }
      );
    }
  },
  created() {
    // this.checkUser();
  }
};
</script>
<style scoped>
.right {
  float: right;
}
.el-menu-item {
  text-align: center;
}
</style>
<style>
ul.el-menu--popup-bottom-start {
  min-width: 100px;
}
</style>

