import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Register from '@/components/Register'
import BooksList from '@/components/BooksList'
import UserInfo from '@/components/UserInfo'
import BookInfo from '@/components/BookInfo'
import BorrowBook from '@/components/BorrowBook'
import UpdatePass from '@/components/UpdatePass'
import UserList from '@/components/UserList'
import BookManage from '@/components/BookManage'
Vue.use(Router)
let router = new Router({
  routes: [{
    path: '/',
    name: 'BooksList',
    component: BooksList
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/user/:id',
    name: 'UserInfo',
    component: UserInfo
  },
  {
    path: '/book/:isbn',
    name: 'BookInfo',
    component: BookInfo
  },
  {
    path: '/borrowbook',
    name: 'BorrowBook',
    component: BorrowBook
  },
  {
    path: '/updatePass',
    name: 'UpdatePass',
    component: UpdatePass
  },
  {
    path: '/admin/manageUser',
    name: 'UserList',
    component: UserList
  }, {
    path: '/admin/manageBook',
    name: 'BookManage',
    component: BookManage
  }
  ]
})
router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/register' || to.path === '/') {
    if (sessionStorage.getItem('isLogin') === 'true' && to.path === '/login') {
      next({path: '/user'})
    } else {
      next()
    }
  } else {
    if (sessionStorage.getItem('isLogin') === 'true') {
      next()
    } else {
      next({path: '/login'})
    }
  }
})

router.afterEach(transition => {

})
export default router
