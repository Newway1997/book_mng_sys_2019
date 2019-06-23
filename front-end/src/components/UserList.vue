<template>
<div>
  
  <el-table :data="users" style="width: 100%">
    <el-table-column prop="id" label="ID"></el-table-column>
    <el-table-column prop="username" label="读者账号"></el-table-column>
    <el-table-column prop="name" label="读者姓名"></el-table-column>
    <el-table-column prop="sex" label="读者性别"></el-table-column>
    <el-table-column prop="phone" label="读者电话"></el-table-column>
    <el-table-column prop="regdate" label="注册时间"></el-table-column>
    <el-table-column label="禁用用户">
      <template slot-scope="scope">
        <el-button
          type="danger"
          value="return"
          @click="disable(scope.row)"
          v-if="scope.row.status===1"
        >禁用</el-button>
        <el-button
          type="success"
          value="return"
          @click="enable(scope.row)"
          v-if="scope.row.status===0"
        >启用</el-button>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>



<style scoped>
.el-row {
  margin: 50px 0;
}
.publish {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
</style>

<script>
export default {
  name: "userList",
  data() {
    return {
      users: []
    };
  },
  methods: {
    getUsers: function() {
      //发送get请求
      this.$http
        .get(`${this.$store.state.origin}/api/user/getAllUsers`, {
          params: { curPage: 0, pageSize: 24 }
        })
        .then(
          res => {
            res.json().then(body => {
              if (body.meta.success === true) {
                this.users = body.data;
                this.users.forEach(e => {
                  e.sex = e.sex === 1 ? "男" : "女";
                });
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    enable(user) {
      this.$http
        .get(`${this.$store.state.origin}/api/user/enableUser`, {
          params: { id: user.id }
        })
        .then(
          res => {
            res.json().then(body => {
              if (body.meta.success === true) {
                this.getUsers();
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    disable(user) {
      this.$http
        .get(`${this.$store.state.origin}/api/user/disableUser`, {
          params: { id: user.id }
        })
        .then(
          res => {
            res.json().then(body => {
              this.getUsers();
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    }
  },
  created() {
    this.getUsers();
  }
};
</script>