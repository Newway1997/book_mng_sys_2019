  <template>
  <el-row>
    <el-col :span="4" :offset="3">
      <el-card :body-style="{ padding: '0px' }">
        <img :src="'/static/img/'+book.imgUrl" class="image">
        <div style="padding: 14px;text-align:left">
          <span>{{book.bookName}}</span>
          <div class="bottom clearfix">
            <span class="publish">{{book.publish}}</span>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="10" :offset="3">
      <ul class="info">
        <li name="bookName"><label>书名</label><span>{{book.bookName}}</span></li>
        <li name="author"><label>作者</label><span>{{book.author}}</span></li>
        <li name="publisher"><label>出版社</label><span>{{book.publisher}}</span></li>
        <li name="unitPrice"><label>价格</label><span>{{book.unitPrice}}</span></li>
        <li name="sumary"><label>简介</label><span>{{book.summary}}</span></li>
        <li name="lastNum"><label>余量</label><span>{{book.lastNum}}</span></li>
        <li>
          <el-button type="primary" @click="borrowBook" :disabled="book.lastNum<1" >借阅</el-button>
        </li>
      </ul>
    </el-col>
  </el-row>
</template>

<script>
import { setTimeout } from 'timers';
export default {
  name: "BorrowBook",
  data() {
    return { book: {} };
  },
  methods: {
    getBook(id) {
      this.$http
        .get(`${this.$store.state.origin}/api/book/getBookDetail`,{params:{isbn:this.$route.params.isbn}})
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
                  this.book = body.data;
                }
              }
            });
          },
          function() {
            console.log("请求失败处理");
          }
        );
    },
    borrowBook() {
      this.$http
        .get(`${this.$store.state.origin}/api/book/borrowBook`,{params:{isbn:this.$route.params.isbn}})
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
                    message: "借阅成功",
                    type: "success",
                    duration: 1000
                  });
                  setTimeout(()=>{this.$router.push({
                    //你需要接受路由的参数再跳转
                    path: "/"
                  });},1000);
                  
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
    this.getBook();
  }
};
</script>

<style scoped>
.el-row{
  margin-top: 100px;
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
ul {
  list-style: none;
  text-align: center;
  
}

ul.info li{
  margin: 10px;
  min-height: 30px;
  line-height: 30px;
  
}
li label{
  float: left;
  font-weight: bold;
}
li span{
 
}
li[name="sumary"] span{
  text-align: left;
}
li[name="bookName"]{
  font-size: 32px;
  font-weight: bold;
}
</style>

