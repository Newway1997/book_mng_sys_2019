  <template>
  <div class="loginContainer">
    <el-col :span="6" :offset="9">
      <el-form ref="form" label-width="80px" :disabled="!updateMod">
        <el-form-item label="姓名">
          <el-input placeholder="姓名" name="name" v-model="reader.name" clearable></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input placeholder="年龄" name="age" v-model="reader.age" clearable></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="reader.sex" name="sex"  placeholder="性别">
            <el-option label="男" :value="1"></el-option>
            <el-option label="女" :value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电话">
          <el-input placeholder="电话" name="phone" v-model="reader.phone" clearable></el-input>
        </el-form-item>
      </el-form>

      <el-button type="primary" v-if="!updateMod" @click="updateMod=!updateMod">修改</el-button>
      <div v-if="updateMod">
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="updateMod=!updateMod">取消</el-button>
      </div>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return { reader: {}, updateMod: false };
  },
  methods: {
    getReaderInfo(id) {
      this.$http.get(`${this.$store.state.origin}/api/reader/getReaderInfo`).then(
        res => {
          res.json().then(body => {
            if (body != null) {
              if (body.meta.success == false) {
                this.$message({
                  showClose: true,
                  message: body.meta.message,
                  type: "error"
                });
              } else {
                this.reader = body.data;
              }
            }
          });
        },
        function() {
          console.log("请求失败处理");
        }
      );
    },
    onSubmit() {
      this.$http.post(`${this.$store.state.origin}/api/reader/updateReaderInfo`, this.reader).then(
        res => {
          if (body.meta.success == true) {
            this.$message("修改成功");
          } else {
            this.$message({
              showClose: true,
              message: body.meta.message,
              type: "error"
            });
          }
        },
        function() {
          console.log("请求失败处理");
        }
      );
      this.updateMod = !this.updateMod;
    }
  },
  created: function() {
    this.getReaderInfo();
  }
};
</script>

<style scoped>
.loginContainer {
  margin: 100px;
}
.el-select {
  display: block;
}
</style>

