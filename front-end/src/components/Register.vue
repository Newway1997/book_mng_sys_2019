<template>
  <div class="registerContainer">
    <el-form :model="ruleForm" ref="ruleForm" label-width="100px" :rules="rules">
      <el-form-item label="账号" prop="username">
        <el-input placeholder="账号" name="username" v-model="ruleForm.username" clearable></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          placeholder="密码"
          name="password"
          v-model="ruleForm.password"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" value="register" @click="register" :loading="isloading">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      isloading: false,
      ruleForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [{ required: true, message: "请输入账号", trigger: "blur" }],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    register() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.isloading = true;
          this.$http
            .post(`${this.$store.state.origin}/api/user/register`, {
              username: this.ruleForm.username,
              password: this.ruleForm.password
            })
            .then(
              res => {
                this.isloading = false;

                res.json().then(body => {
                  if (body.meta.success === true) {
                    this.$message({
                      showClose: true,
                      message: "注册成功",
                      type: "success",
                      duration: 1000
                    });
                    setTimeout(() => {
                      //跳转
                      this.$router.push({
                        path: "/"
                      });
                    }, 1000);
                  }else{
                    this.$message({
                      showClose: true,
                      message: body.meta.message,
                      type: "error"
                    });
                  }
                });
              },
              function() {
                this.isloading = false;
                this.$message({
                  showClose: true,
                  message: "暂时无法注册",
                  type: "error"
                });
                console.log("请求失败处理");
              }
            );
        } else {
          this.$message({
            showClose: true,
            message: "请输入账号和密码",
            type: "error"
          });
        }
      });
    }
  }
};
</script>
<style scoped>
.el-form {
  width: 360px;
  text-align: left;
}

.registerContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
