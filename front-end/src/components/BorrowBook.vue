<template>
  <el-table :data="borrowBook" style="width: 100%">
    <el-table-column prop="book.isbn" label="ISBN" width="180"></el-table-column>
    <el-table-column prop="borrowDate" label="借阅日期" width="180"></el-table-column>
    <el-table-column prop="returnDate" label="归还日期"></el-table-column>
    <el-table-column label="归还图书">
      <template slot-scope="scope">
        <el-button type="primary" value="return" @click="returnBook(scope.row)">归还</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { setTimeout } from 'timers';
export default {
  name: "BorrowBook",
  data() {
    return { borrowBook: [] };
  },
  methods: {
    getBorrowBook() {
      this.$http
        .get(`${this.$store.state.origin}/api/borrowBook/getBorrowBooks`)
        .then(
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
                  body.data.forEach(element => {
                    element.returndate = new Date(
                      element.returndate
                    ).toLocaleDateString();
                    element.borrowdate = new Date(
                      element.borrowdate
                    ).toLocaleDateString();
                  });
                  this.borrowBook = body.data;
                }
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    returnBook(params) {
      let id=params.id;
      let isbn=params.book.isbn;
      this.$http
        .get(`${this.$store.state.origin}/api/book/returnBook`, {
          params: { id:id,isbn:isbn }
        })
        .then(
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
                  this.$message({
                    showClose: true,
                    message: "归还成功",
                    type: "success",
                    duration: 1000
                  });
                  setTimeout(()=>this.$router.go(0),2000);
                  
                }
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    }
  },
  created: function() {
    this.getBorrowBook();
  }
};
</script>

<style scoped>
</style>
