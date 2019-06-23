<template>
  <div class="updatePassContainer">
    <el-form :model="ruleForm" ref="ruleForm" label-width="100px" :rules="rules">
      <el-form-item label="旧密码" prop="oldPass">
        <el-input
          type="password"
          placeholder="旧密码"
          name="oldPass"
          v-model="ruleForm.oldPass"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPass">
        <el-input
          type="password"
          placeholder="密码"
          name="newPass"
          v-model="ruleForm.newPass"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" value="update" @click="update" :loading="isloading">确认</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "updatePass",
  data() {
    return {
      isloading: false,
      ruleForm: {
        oldPass: "",
        newPass: ""
      },
      rules: {
        oldPass: [{ required: true, message: "请输入旧密码", trigger: "blur" }],
        newPass: [{ required: true, message: "请输入新密码", trigger: "blur" }]
      }
    };
  },
  methods: {
    update() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.isloading = true;
          this.$http
            .post(`${this.$store.state.origin}/api/user/updatePass`, {
              oldPass: this.ruleForm.oldPass,
              newPass: this.ruleForm.newPass
            })
            .then(
              res => {
                this.isloading = false;

                res.json().then(body => {
                  if (body.meta.success === true) {
                    this.$message({
                      showClose: true,
                      message: "修改成功",
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
                  message: "暂时无法修改",
                  type: "error"
                });
                console.log("请求失败处理");
              }
            );
        } else {
          this.$message({
            showClose: true,
            message: "请输入旧密码和新密码",
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

.updatePassContainer {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
