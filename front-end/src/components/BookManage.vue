<template>
  <div>
    <el-button class="upload" @click="preUpload">上传图书</el-button>

    <el-table :data="books" style="width: 100%">
      <el-table-column prop="isbn" label="ISBN"></el-table-column>
      <el-table-column prop="bookName" label="书名"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="publisher" label="出版社"></el-table-column>
      <el-table-column label="修改图书">
        <template slot-scope="scope">
          <el-button type="primary" value="return" @click="update(scope.row)">修改图书</el-button>
        </template>
      </el-table-column>
      <el-table-column label="禁用图书">
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
    <el-dialog title="书籍" :visible.sync="dialogFormVisible">
      <el-form :model="form" enctype="multipart/form-data" ref="form" :rules="rules">
        <el-form-item label="ISBN" prop="isbn" :label-width="formLabelWidth">
          <el-input v-model="form.isbn" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="书籍名称" prop="bookName" :label-width="formLabelWidth">
          <el-input v-model="form.bookName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="类别" prop="categoryId" :label-width="formLabelWidth">
          <el-select v-model="form.categoryId" name="categoryId" placeholder="请选择活动区域">
            <el-option
              v-for="category in categorys"
              :label="category.categoryName"
              :value="category.id"
              :key="category.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="作者" prop="author" :label-width="formLabelWidth">
          <el-input v-model="form.author" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出版社" prop="publisher" :label-width="formLabelWidth">
          <el-input v-model="form.publisher" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出版时间" prop="publishDate" :label-width="formLabelWidth">
          <el-date-picker v-model="form.publishDate" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice" :label-width="formLabelWidth">
          <el-input v-model="form.unitPrice" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="img" :label-width="formLabelWidth">
          <!-- <el-input type="file" v-model="form.imgUrl" @change="setImg($event)" autocomplete="off"></el-input> -->
          <input type="file" @change="setImg($event)">
        </el-form-item>

        <el-form-item label="简介" prop="summary" :label-width="formLabelWidth">
          <el-input v-model="form.summary" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数量" prop="allNum" :label-width="formLabelWidth">
          <el-input v-model="form.allNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="标签" prop="tags" :label-width="formLabelWidth">
          <el-input v-model="form.tags" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="upload">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>
.upload {
  float: left;
}
.el-row {
  margin: 50px 0;
}
.publish {
  font-size: 13px;
  color: #999;
}
.el-select {
  display: block;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
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
import { constants } from "fs";
import { setTimeout } from "timers";
export default {
  data() {
    return {
      books: [],
      oringnIsbn: "",
      dialogFormVisible: false,
      mode: 0,
      categorys: [],
      form: {
        isbn: "",
        categoryId: "",
        bookName: "",
        author: "",
        publisher: "",
        publishDate: "",
        unitPrice: "",
        img: "",
        summary: "",
        allNum: 0,
        tags: ""
      },
      formLabelWidth: "120px",
      rules: {
        isbn: [{ required: true, message: "请输入ISBN", trigger: "blur" }],
        categoryId: [
          { required: true, message: "请输入类别", trigger: "blur" }
        ],
        bookName: [{ required: true, message: "请输入书名", trigger: "blur" }],
        author: [{ required: true, message: "请输入作者", trigger: "blur" }],
        publisher: [
          { required: true, message: "请输入出版社", trigger: "blur" }
        ],
        publishDate: [
          { required: true, message: "请输入出版日期", trigger: "blur" }
        ],
        unitPrice: [{ required: true, message: "请输入单价", trigger: "blur" }],
        img: [{ required: true, message: "请输入图片", trigger: "blur" }],
        summary: [{ required: true, message: "请输入简介", trigger: "blur" }],
        allNum: [{ required: true, message: "请输入数量", trigger: "blur" }]
      }
    };
  },
  computed: {
    showBooks() {
      const bookArr = [];
      this.books.forEach((item, index) => {
        const row = Math.floor(index / 5); //4代表4条为一行，随意更改
        if (!bookArr[row]) {
          bookArr[row] = [];
        }
        bookArr[row].push(item);
      });
      return bookArr;
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    getBooks: function() {
      //发送get请求
      this.$http
        .get(`${this.$store.state.origin}/api/book/getBooks`, {
          params: { curPage: 0, pageSize: 24 }
        })
        .then(
          res => {
            res.json().then(body => {
              if (body.meta.success === true) {
                this.books = body.data;
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    routeToBook(isbn) {
      this.$router.push({
        //你需要接受路由的参数再跳
        path: "/book/" + isbn
      });
    },
    enable(book) {
      this.$http
        .get(`${this.$store.state.origin}/api/book/enableBook`, {
          params: { isbn: book.isbn }
        })
        .then(
          res => {
            res.json().then(body => {
              if (body.meta.success === true) {
                this.getBooks();
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    update(book) {
      this.mode = 1;
      this.dialogFormVisible = true;
      this.rules.img[0].required = false;
      this.oringnIsbn = book.isbn;
      this.$http
        .get(`${this.$store.state.origin}/api/book/getBookDetail`, {
          params: { isbn: this.oringnIsbn }
        })
        .then(
          res => {
            res.json().then(body => {
              if (body.meta.success === true) {
                Object.assign(this.form, body.data);
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    setImg(event) {
      console.log(event);
      this.form.img = event.target.files[0]; // get input file object
    },
    disable(book) {
      this.$http
        .get(`${this.$store.state.origin}/api/book/disableBook`, {
          params: { isbn: book.isbn }
        })
        .then(
          res => {
            res.json().then(body => {
              this.getBooks();
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    preUpload() {
      this.mode = 0;
      this.rules.img[0].required = true;
      this.dialogFormVisible = true;
      for (let key in this.form) {
            this.form[key]="";
          }
    },
    upload() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          var that = this;
          //保证date为字符串
          if(this.mode===1){
            this.form.publishDate=new Date(this.form.publishDate);
          }
          var formData = new FormData();
          formData.append("img", this.form.img);
          for (let key in this.form) {
            formData.append(key, this.form[key]);
          }
          let url;
          // specify Content-Type, with formData as well
          if (this.mode === 0) {
            url = `${this.$store.state.origin}/api/book/uploadBook`;
          } else {
            url = `${this.$store.state.origin}/api/book/updateBook`;
            
            formData.append("oringnIsbn", this.oringnIsbn);
          }
          let messageFront =this.mode === 0?"添加":"修改";
          this.$http
            .post(url, formData, {
              headers: { "Content-Type": "multipart/form-data" }
            })
            .then(
              res => {
                
                res.json().then(result => {
                  this.dialogFormVisible = false;
                  this.$message({
                    showClose: true,
                    message: messageFront + "成功",
                    type: "success"
                  });
                  setTimeout(() => {
                    this.getBooks();
                  }, 2000);
                });
              },
              function(res) {
                this.$message({
                  showClose: true,
                  message: messageFront + "失败:" + res.meta.message,
                  type: "error"
                });
              }
            );
          // this.$http
          //     .post("", {
          //       params: { isbn: book.isbn }
          //     })
          //
        }
      });
    },
    getCategory() {
      this.$http.get(`${this.$store.state.origin}/api/book/getCategory`).then(
        res => {
          res.json().then(body => {
            this.categorys = body.data;
          });
        },
        function() {
          console.log("请求失败处理");
        }
      );
    }
  },
  created() {
    this.getBooks();
    this.getCategory();
  }
};
</script>