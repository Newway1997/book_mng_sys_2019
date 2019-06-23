<template>
<div>

  <el-row v-for="(books,index) in showBooks" :key="index">
    <el-col :span="4" v-for="(book,index) in books" :key="book.isbn" :offset="index > 0 ?  1 : 0">
      <el-card :body-style="{ padding: '0px' }">
        <img :src="baseUrl+book.imgUrl" class="image">
        <div style="padding: 14px;text-align:left">
          <span>{{book.bookName}}</span>
          <div class="bottom clearfix">
            <span class="publish">{{book.publisher}}</span>
            <el-button type="text" class="button" @click="routeToBook(book.isbn)">详情</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
</div>
</template>



<style scoped>
.el-row{
  margin:50px 0;
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
  data() {
    return {
      books: [],
      baseUrl:this.$store.state.origin+'/img/'
    };
  },
  computed: {
    showBooks () {
      const bookArr = [];
      this.books.forEach((item, index) => {
        const row = Math.floor(index / 5)//4代表4条为一行，随意更改
        if (!bookArr[row]) {
          bookArr[row] = []
        }
        bookArr[row].push(item)
      })
      return bookArr
    }
  },
  methods: {
    getBooks: function() {
      //发送get请求
      this.$http.get(`${this.$store.state.origin}/api/book/getBooks`,{params:{curPage:0,pageSize:24}}).then(
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
    }
  },
  created() {
    this.getBooks();
  }
};
</script>